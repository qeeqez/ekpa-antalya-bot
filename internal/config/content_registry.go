package config

import (
	"fmt"
	"maps"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

func (r *ContentRepository) registerScreens(screens []domain.ScreenTemplate) error {
	for i := range screens {
		screen := &screens[i]
		if _, exists := r.catalog.Screens[screen.ID]; exists {
			return fmt.Errorf("duplicate screen ID: %s", screen.ID)
		}
		r.catalog.Screens[screen.ID] = screen
	}
	return nil
}

func (r *ContentRepository) registerCommands(commands []domain.CommandTemplate) error {
	for i := range commands {
		command := &commands[i]
		if _, exists := r.catalog.Commands[command.Command]; exists {
			return fmt.Errorf("duplicate command: %s", command.Command)
		}
		r.catalog.Commands[command.Command] = command
		r.catalog.CommandOrder = append(r.catalog.CommandOrder, command.Command)
	}
	return nil
}

func (r *ContentRepository) materializeScreens() map[string]*domain.Screen {
	screens := make(map[string]*domain.Screen, len(r.catalog.Screens))
	for id, template := range r.catalog.Screens {
		screens[id] = template.ToScreen()
	}
	return screens
}

func cloneStringMap(values map[string]string) map[string]string {
	if len(values) == 0 {
		return nil
	}

	clone := make(map[string]string, len(values))
	maps.Copy(clone, values)

	return clone
}

func (r *ContentRepository) applyLocalizedScreens(screens []LocalizedScreen, localeCode, path string) error {
	bundle := r.catalog.Bundle(localeCode)
	for _, localized := range screens {
		if _, ok := r.catalog.Screens[localized.ID]; !ok {
			return fmt.Errorf("localized screen %s in %s has no shared base screen", localized.ID, path)
		}

		bundle.Screens[localized.ID] = domain.ScreenLocale{
			Text:        localized.Text,
			ButtonTexts: cloneStringMap(localized.ButtonTexts),
		}
	}

	return nil
}

func (r *ContentRepository) applyLocalizedCommands(commands []LocalizedCommand, localeCode, path string) error {
	bundle := r.catalog.Bundle(localeCode)
	for _, localized := range commands {
		if _, found := r.catalog.Commands[localized.Command]; !found {
			return fmt.Errorf("localized command %s in %s has no shared base command", localized.Command, path)
		}

		bundle.Commands[localized.Command] = domain.CommandLocale{
			Description: localized.Description,
		}
	}

	return nil
}

func (r *ContentRepository) applyLocalizedFragments(fragments map[string]string, localeCode string) error {
	if len(fragments) == 0 {
		return nil
	}

	bundle := r.catalog.Bundle(locale.Normalize(localeCode))
	maps.Copy(bundle.Fragments, fragments)

	return nil
}
