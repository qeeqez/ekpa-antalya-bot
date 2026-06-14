package config

import (
	"fmt"
	"sort"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

// AuditLocaleParity compares localized bundles against the default locale bundle and reports mismatches.
func (r *ContentRepository) AuditLocaleParity() []string {
	defaultBundle, ok := r.catalog.Bundles[locale.DefaultLocale]
	if !ok || defaultBundle == nil {
		return []string{"missing default locale bundle for " + locale.DefaultLocale}
	}

	problems := make([]string, 0)
	expectedScreens := make(map[string]struct{}, len(defaultBundle.Screens))
	expectedCommands := make(map[string]struct{}, len(defaultBundle.Commands))
	expectedFragments := make(map[string]struct{}, len(defaultBundle.Fragments))

	for screenID := range defaultBundle.Screens {
		expectedScreens[screenID] = struct{}{}
	}
	for commandID := range defaultBundle.Commands {
		expectedCommands[commandID] = struct{}{}
	}
	for fragmentID := range defaultBundle.Fragments {
		expectedFragments[fragmentID] = struct{}{}
	}

	for _, localeCode := range locale.SupportedLocales {
		bundle, ok := r.catalog.Bundles[localeCode]
		if !ok || bundle == nil {
			problems = append(problems, "missing locale bundle for "+localeCode)
			continue
		}

		problems = append(problems, compareLocaleKeys("screen", localeCode, expectedScreens, bundle.Screens)...)
		problems = append(problems, compareLocaleKeys("command", localeCode, expectedCommands, bundle.Commands)...)
		problems = append(problems, compareLocaleKeys("fragment", localeCode, expectedFragments, bundle.Fragments)...)
		problems = append(problems, compareLocalizedButtonKeys(localeCode, r.catalog.Screens, bundle.Screens)...)
	}

	sort.Strings(problems)
	return problems
}

func compareLocaleKeys[T any](kind, localeCode string, expected map[string]struct{}, actual map[string]T) []string {
	problems := make([]string, 0)

	for key := range expected {
		if _, ok := actual[key]; !ok {
			problems = append(problems, fmt.Sprintf("locale %s missing %s %s", localeCode, kind, key))
		}
	}

	for key := range actual {
		if _, ok := expected[key]; !ok {
			problems = append(problems, fmt.Sprintf("locale %s has extra %s %s", localeCode, kind, key))
		}
	}

	return problems
}

func compareLocalizedButtonKeys(localeCode string, shared map[string]*domain.ScreenTemplate, localized map[string]domain.ScreenLocale) []string {
	problems := make([]string, 0)

	for screenID, localeScreen := range localized {
		base := shared[screenID]
		if base == nil || len(localeScreen.ButtonTexts) == 0 {
			continue
		}

		expected := make(map[string]struct{})
		for _, row := range base.InlineKeyboard.Rows {
			for _, button := range row.Buttons {
				expected[button.ID] = struct{}{}
			}
		}

		for buttonID := range localeScreen.ButtonTexts {
			if _, ok := expected[buttonID]; !ok {
				problems = append(problems, fmt.Sprintf("locale %s screen %s has extra button text %s", localeCode, screenID, buttonID))
			}
		}
	}

	return problems
}
