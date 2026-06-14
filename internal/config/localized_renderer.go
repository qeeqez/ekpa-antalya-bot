package config

import (
	"maps"
	"strings"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

// LocalizedContentRenderer turns catalog data into locale-specific runtime objects.
type LocalizedContentRenderer struct {
	catalog    *domain.ContentCatalog
	navigation *domain.NavigationRegistry
}

// NewLocalizedContentRenderer creates a renderer for the given catalog and navigation registry.
func NewLocalizedContentRenderer(catalog *domain.ContentCatalog, navigation *domain.NavigationRegistry) *LocalizedContentRenderer {
	return &LocalizedContentRenderer{
		catalog:    catalog,
		navigation: navigation,
	}
}

// Screen returns a localized screen with automatic navigation applied.
func (r *LocalizedContentRenderer) Screen(screenID, localeCode string, screens map[string]*domain.Screen) (*domain.Screen, error) {
	screen, ok := screens[screenID]
	if !ok {
		return nil, domain.ErrScreenNotFound(screenID)
	}

	enhancedScreen := r.cloneScreen(screen)
	r.applyScreenLocale(enhancedScreen, locale.DefaultLocale)
	if normalized := locale.Normalize(localeCode); normalized != locale.DefaultLocale {
		r.applyScreenLocale(enhancedScreen, normalized)
	}

	enhancedScreen = r.navigation.AddAutoNavigation(enhancedScreen, locale.Normalize(localeCode))
	r.expandScreenFragments(enhancedScreen, locale.Normalize(localeCode))
	if err := enhancedScreen.Validate(); err != nil {
		return nil, err
	}

	return enhancedScreen, nil
}

// Commands returns localized commands for a locale.
func (r *LocalizedContentRenderer) Commands(localeCode string) *domain.CommandRegistry {
	normalized := locale.Normalize(localeCode)
	registry := &domain.CommandRegistry{
		Commands: make([]domain.Command, 0, len(r.catalog.CommandOrder)),
	}

	for _, name := range r.catalog.CommandOrder {
		cmd := r.catalog.Commands[name]
		localized := cmd.ToCommand()
		r.applyCommandLocale(localized, locale.DefaultLocale)
		if normalized != locale.DefaultLocale {
			r.applyCommandLocale(localized, normalized)
		}
		localized.Description = r.expandText(localized.Description, normalized)
		registry.Commands = append(registry.Commands, *localized)
	}

	return registry
}

func (r *LocalizedContentRenderer) cloneScreen(screen *domain.Screen) *domain.Screen {
	clone := *screen
	clone.InlineKeyboard.Rows = make([]domain.ButtonRow, len(screen.InlineKeyboard.Rows))
	for i := range screen.InlineKeyboard.Rows {
		clone.InlineKeyboard.Rows[i].Buttons = make([]domain.Button, len(screen.InlineKeyboard.Rows[i].Buttons))
		copy(clone.InlineKeyboard.Rows[i].Buttons, screen.InlineKeyboard.Rows[i].Buttons)
	}
	return &clone
}

func cloneStringMap(values map[string]string) map[string]string {
	if len(values) == 0 {
		return nil
	}

	clone := make(map[string]string, len(values))
	maps.Copy(clone, values)

	return clone
}

func (r *LocalizedContentRenderer) expandScreenFragments(screen *domain.Screen, localeCode string) {
	screen.Text = r.expandText(screen.Text, localeCode)
	for rowIdx := range screen.InlineKeyboard.Rows {
		for btnIdx := range screen.InlineKeyboard.Rows[rowIdx].Buttons {
			button := &screen.InlineKeyboard.Rows[rowIdx].Buttons[btnIdx]
			button.Text = r.expandText(button.Text, localeCode)
		}
	}
}

func (r *LocalizedContentRenderer) expandText(text, localeCode string) string {
	if text == "" {
		return text
	}

	expanded := text
	for range 5 {
		next := r.replaceFragmentRefs(expanded, localeCode)
		if next == expanded {
			break
		}
		expanded = next
	}

	return expanded
}

func (r *LocalizedContentRenderer) replaceFragmentRefs(text, localeCode string) string {
	fragments := r.fragmentSet(localeCode)
	for key, value := range fragments {
		text = replaceFragment(text, key, value)
	}
	return text
}

func (r *LocalizedContentRenderer) fragmentSet(localeCode string) map[string]string {
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

func replaceFragment(text, key, value string) string {
	placeholder := "{{" + key + "}}"
	return strings.ReplaceAll(text, placeholder, value)
}

func (r *LocalizedContentRenderer) applyScreenLocale(screen *domain.Screen, localeCode string) {
	bundle, ok := r.catalog.Bundles[localeCode]
	if !ok {
		return
	}

	localeData, ok := bundle.Screens[screen.ID]
	if !ok {
		return
	}

	if localeData.Text != "" {
		screen.Text = localeData.Text
	}

	if len(localeData.ButtonTexts) == 0 {
		return
	}

	for rowIdx := range screen.InlineKeyboard.Rows {
		for btnIdx := range screen.InlineKeyboard.Rows[rowIdx].Buttons {
			button := &screen.InlineKeyboard.Rows[rowIdx].Buttons[btnIdx]
			if text, ok := localeData.ButtonTexts[button.ID]; ok && text != "" {
				button.Text = text
			}
		}
	}
}

func (r *LocalizedContentRenderer) applyCommandLocale(command *domain.Command, localeCode string) {
	bundle, ok := r.catalog.Bundles[localeCode]
	if !ok {
		return
	}

	localeData, ok := bundle.Commands[command.Command]
	if !ok {
		return
	}

	if localeData.Description != "" {
		command.Description = localeData.Description
	}
}
