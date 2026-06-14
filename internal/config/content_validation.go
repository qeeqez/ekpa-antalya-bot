package config

import (
	"errors"
	"fmt"
	"maps"
	"regexp"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

var fragmentPattern = regexp.MustCompile(`\{\{([a-zA-Z0-9_]+)\}\}`)
var markdownV2RenderableStripPatterns = []*regexp.Regexp{
	regexp.MustCompile("(?s)```.*?```"),
	regexp.MustCompile("`[^`]*`"),
	regexp.MustCompile(`\[[^\]]*\]\([^)]+\)`),
}

var markdownV2ReservedPlain = map[byte]struct{}{
	'.': {},
	'!': {},
	'(': {},
	')': {},
	'-': {},
}

func (r *ContentRepository) validateLoadedCatalog() error {
	if err := r.validateMergedScreens(); err != nil {
		return err
	}

	if err := r.validateRequiredLocaleBundles(); err != nil {
		return err
	}

	if err := r.validateDefaultFragments(); err != nil {
		return err
	}

	if err := r.validateMarkdownV2Content(); err != nil {
		return err
	}

	return nil
}

func (r *ContentRepository) validateMergedScreens() error {
	for _, screen := range r.catalog.Screens {
		if err := r.validateSharedScreen(screen); err != nil {
			return fmt.Errorf("invalid shared screen %s: %w", screen.ID, err)
		}
	}

	return nil
}

func (r *ContentRepository) validateRequiredLocaleBundles() error {
	for _, localeCode := range locale.SupportedReleaseLocales() {
		bundle, ok := r.catalog.Bundles[localeCode]
		if !ok || bundle == nil {
			return fmt.Errorf("missing locale bundle for %s", localeCode)
		}
	}

	return nil
}

func (r *ContentRepository) validateDefaultFragments() error {
	defaultBundle, ok := r.catalog.Bundles[locale.DefaultLocale]
	if !ok || defaultBundle == nil {
		return fmt.Errorf("missing default locale bundle for %s", locale.DefaultLocale)
	}

	requiredFragments := r.collectFragmentReferences()
	for key := range requiredFragments {
		if _, ok := defaultBundle.Fragments[key]; !ok {
			return fmt.Errorf("missing default fragment %q", key)
		}
	}

	return nil
}

func (r *ContentRepository) collectFragmentReferences() map[string]struct{} {
	required := make(map[string]struct{})

	for _, screen := range r.catalog.Screens {
		r.collectFragmentReferencesFromText(required, screen.Text)
		for _, row := range screen.InlineKeyboard.Rows {
			for _, button := range row.Buttons {
				r.collectFragmentReferencesFromText(required, button.Text)
			}
		}
	}

	for _, bundle := range r.catalog.Bundles {
		for _, screen := range bundle.Screens {
			r.collectFragmentReferencesFromText(required, screen.Text)
			for _, text := range screen.ButtonTexts {
				r.collectFragmentReferencesFromText(required, text)
			}
		}
		for _, command := range bundle.Commands {
			r.collectFragmentReferencesFromText(required, command.Description)
		}
		for _, text := range bundle.Fragments {
			r.collectFragmentReferencesFromText(required, text)
		}
	}

	return required
}

func (r *ContentRepository) collectFragmentReferencesFromText(required map[string]struct{}, text string) {
	for _, match := range fragmentPattern.FindAllStringSubmatch(text, -1) {
		if len(match) < 2 {
			continue
		}
		required[match[1]] = struct{}{}
	}
}

func (r *ContentRepository) validateSharedScreen(screen *domain.ScreenTemplate) error {
	if screen.ID == "" {
		return errors.New("screen ID cannot be empty")
	}

	for rowIdx, row := range screen.InlineKeyboard.Rows {
		for btnIdx, btn := range row.Buttons {
			if err := r.validateSharedButton(btn); err != nil {
				return fmt.Errorf("invalid button at row %d, position %d: %w", rowIdx, btnIdx, err)
			}
		}
	}

	return nil
}

func (r *ContentRepository) validateSharedButton(button domain.Button) error {
	switch button.Type {
	case domain.ButtonTypeCallback:
		if button.CallbackData == "" {
			return domain.ErrInvalidButton("callback button must have callback data")
		}
	case domain.ButtonTypeURL:
		if button.URL == "" {
			return domain.ErrInvalidButton("URL button must have URL")
		}
	default:
		return domain.ErrInvalidButton("unknown button type: " + string(button.Type))
	}

	return nil
}

func (r *ContentRepository) validateMarkdownV2Content() error {
	for _, screen := range r.catalog.Screens {
		if screen.ParseMode != domain.ParseModeMarkdownV2 {
			continue
		}

		for localeCode := range r.catalog.Bundles {
			renderedText := r.renderMarkdownV2Text(screen.ID, localeCode)
			if err := validateMarkdownV2Text(renderedText); err != nil {
				return fmt.Errorf("invalid MarkdownV2 text for screen %s locale %s: %w", screen.ID, localeCode, err)
			}
		}
	}

	return nil
}

func (r *ContentRepository) renderMarkdownV2Text(screenID, localeCode string) string {
	screen := r.catalog.Screens[screenID]
	if screen == nil {
		return ""
	}

	text := screen.Text
	normalized := locale.Normalize(localeCode)
	if bundle, ok := r.catalog.Bundles[normalized]; ok {
		if localizedScreen, ok := bundle.Screens[screenID]; ok && localizedScreen.Text != "" {
			text = localizedScreen.Text
		}
	}

	return expandMarkdownV2Text(text, r.renderMarkdownV2Fragments(localeCode))
}

func (r *ContentRepository) renderMarkdownV2Fragments(localeCode string) map[string]string {
	normalized := locale.Normalize(localeCode)
	merged := make(map[string]string)

	if base, ok := r.catalog.Bundles[locale.DefaultLocale]; ok {
		maps.Copy(merged, base.Fragments)
	}

	if normalized != locale.DefaultLocale {
		if overlay, ok := r.catalog.Bundles[normalized]; ok {
			maps.Copy(merged, overlay.Fragments)
		}
	}

	return merged
}

func expandMarkdownV2Text(text string, fragments map[string]string) string {
	if text == "" {
		return text
	}

	expanded := text
	for range 5 {
		next := expanded
		for key, value := range fragments {
			next = replaceFragment(next, key, value)
		}
		if next == expanded {
			break
		}
		expanded = next
	}

	return expanded
}

func validateMarkdownV2Text(text string) error {
	if text == "" {
		return nil
	}

	sanitized := text
	for _, pattern := range markdownV2RenderableStripPatterns {
		sanitized = pattern.ReplaceAllString(sanitized, "")
	}

	for i := range len(sanitized) {
		if _, ok := markdownV2ReservedPlain[sanitized[i]]; !ok {
			continue
		}
		if i > 0 && sanitized[i-1] == '\\' {
			continue
		}
		return fmt.Errorf("unescaped reserved character %q", sanitized[i])
	}

	return nil
}
