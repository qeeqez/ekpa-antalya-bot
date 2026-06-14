package config

import (
	"fmt"
	"os"
	"path/filepath"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
	"gopkg.in/yaml.v3"
)

// ContentRepository manages all bot content (screens, commands, etc.)
type ContentRepository struct {
	screens    map[string]*domain.Screen
	commands   *domain.CommandRegistry
	navigation *domain.NavigationRegistry
}

// ContentFile represents a content YAML file structure
type ContentFile struct {
	Version  string           `yaml:"version"`
	Screens  []domain.Screen  `yaml:"screens"`
	Commands []domain.Command `yaml:"commands,omitempty"`
}

// NewContentRepository creates and initializes a new content repository
func NewContentRepository(contentDir string) (*ContentRepository, error) {
	repo := &ContentRepository{
		screens: make(map[string]*domain.Screen),
		commands: &domain.CommandRegistry{
			Commands: []domain.Command{},
		},
		navigation: domain.NewNavigationRegistry(),
	}

	// Load content first
	if err := repo.loadContent(contentDir); err != nil {
		return nil, err
	}

	// Automatically build navigation hierarchy from screen relationships
	repo.navigation.BuildFromScreens(repo.screens)

	return repo, nil
}

// loadContent loads all content files from the specified directory
func (r *ContentRepository) loadContent(contentDir string) error {
	entries, err := os.ReadDir(contentDir)
	if err != nil {
		return fmt.Errorf("failed to read content directory: %w", err)
	}

	if err := r.loadAllFiles(contentDir, entries); err != nil {
		return err
	}

	return r.validateAllScreens()
}

// loadAllFiles loads all YAML files from the directory
func (r *ContentRepository) loadAllFiles(contentDir string, entries []os.DirEntry) error {
	for _, entry := range entries {
		if !r.isYAMLFile(entry) {
			continue
		}

		filePath := filepath.Join(contentDir, entry.Name())
		if err := r.loadContentFile(filePath); err != nil {
			return fmt.Errorf("failed to load %s: %w", entry.Name(), err)
		}
	}
	return nil
}

// isYAMLFile checks if the entry is a YAML file
func (r *ContentRepository) isYAMLFile(entry os.DirEntry) bool {
	return !entry.IsDir() && filepath.Ext(entry.Name()) == ".yaml"
}

// validateAllScreens validates all loaded screens
func (r *ContentRepository) validateAllScreens() error {
	for _, screen := range r.screens {
		if err := screen.Validate(); err != nil {
			return fmt.Errorf("invalid screen %s: %w", screen.ID, err)
		}
	}
	return nil
}

// loadContentFile loads a single content file
func (r *ContentRepository) loadContentFile(path string) error {
	data, err := os.ReadFile(path)
	if err != nil {
		return err
	}

	content, err := r.parseContentFile(data)
	if err != nil {
		return err
	}

	if err := r.registerScreens(content.Screens); err != nil {
		return err
	}

	r.registerCommands(content.Commands)
	return nil
}

// parseContentFile parses YAML content into ContentFile structure
func (r *ContentRepository) parseContentFile(data []byte) (*ContentFile, error) {
	var content ContentFile
	if err := yaml.Unmarshal(data, &content); err != nil {
		return nil, fmt.Errorf("failed to parse content: %w", err)
	}
	return &content, nil
}

// registerScreens registers all screens from a content file
func (r *ContentRepository) registerScreens(screens []domain.Screen) error {
	for i := range screens {
		screen := &screens[i]
		if _, exists := r.screens[screen.ID]; exists {
			return fmt.Errorf("duplicate screen ID: %s", screen.ID)
		}
		r.screens[screen.ID] = screen
	}
	return nil
}

// registerCommands registers all commands from a content file
func (r *ContentRepository) registerCommands(commands []domain.Command) {
	r.commands.Commands = append(r.commands.Commands, commands...)
}

// GetScreen returns a screen by ID with automatic navigation buttons
func (r *ContentRepository) GetScreen(screenID string) (*domain.Screen, error) {
	return r.GetScreenForLocale(screenID, locale.DefaultLocale)
}

// GetScreenForLocale returns a screen by ID localized for the given Telegram locale.
func (r *ContentRepository) GetScreenForLocale(screenID, localeCode string) (*domain.Screen, error) {
	screen, ok := r.screens[screenID]
	if !ok {
		return nil, domain.ErrScreenNotFound(screenID)
	}

	enhancedScreen := r.cloneScreen(screen)
	r.applyScreenLocale(enhancedScreen, locale.Normalize(localeCode))

	// Add automatic navigation buttons using localized labels.
	enhancedScreen = r.navigation.AddAutoNavigation(enhancedScreen, locale.Normalize(localeCode))
	return enhancedScreen, nil
}

// GetCommands returns the command registry
func (r *ContentRepository) GetCommands() *domain.CommandRegistry {
	return r.GetCommandsForLocale(locale.DefaultLocale)
}

// GetCommandsForLocale returns the command registry localized for the given Telegram locale.
func (r *ContentRepository) GetCommandsForLocale(localeCode string) *domain.CommandRegistry {
	normalized := locale.Normalize(localeCode)
	registry := &domain.CommandRegistry{
		Commands: make([]domain.Command, 0, len(r.commands.Commands)),
	}

	for _, cmd := range r.commands.Commands {
		localized := cmd
		if localeData, ok := cmd.Locales[normalized]; ok && localeData.Description != "" {
			localized.Description = localeData.Description
		}
		registry.Commands = append(registry.Commands, localized)
	}

	return registry
}

// GetAllScreens returns all registered screens
func (r *ContentRepository) GetAllScreens() map[string]*domain.Screen {
	return r.screens
}

// GetNavigationRegistry returns the navigation registry (for debugging/inspection)
func (r *ContentRepository) GetNavigationRegistry() *domain.NavigationRegistry {
	return r.navigation
}

func (r *ContentRepository) cloneScreen(screen *domain.Screen) *domain.Screen {
	clone := *screen
	clone.InlineKeyboard.Rows = make([]domain.ButtonRow, len(screen.InlineKeyboard.Rows))
	for i := range screen.InlineKeyboard.Rows {
		clone.InlineKeyboard.Rows[i].Buttons = make([]domain.Button, len(screen.InlineKeyboard.Rows[i].Buttons))
		copy(clone.InlineKeyboard.Rows[i].Buttons, screen.InlineKeyboard.Rows[i].Buttons)
	}
	return &clone
}

func (r *ContentRepository) applyScreenLocale(screen *domain.Screen, localeCode string) {
	localeData, ok := screen.Locales[localeCode]
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
