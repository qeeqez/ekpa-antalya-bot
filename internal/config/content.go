package config

import (
	"errors"
	"fmt"
	"maps"
	"os"
	"path/filepath"
	"regexp"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
	"gopkg.in/yaml.v3"
)

var fragmentPattern = regexp.MustCompile(`\{\{([a-zA-Z0-9_]+)\}\}`)

// ContentRepository manages all bot content (screens, commands, etc.)
type ContentRepository struct {
	catalog    *domain.ContentCatalog
	screens    map[string]*domain.Screen
	navigation *domain.NavigationRegistry
	renderer   *LocalizedContentRenderer
}

// ContentFile represents a content YAML file structure
type ContentFile struct {
	Version  string                   `yaml:"version"`
	Screens  []domain.ScreenTemplate  `yaml:"screens"`
	Commands []domain.CommandTemplate `yaml:"commands,omitempty"`
}

// LocaleContentFile represents localized overrides for screens and commands.
type LocaleContentFile struct {
	Version   string             `yaml:"version"`
	Screens   []LocalizedScreen  `yaml:"screens,omitempty"`
	Commands  []LocalizedCommand `yaml:"commands,omitempty"`
	Fragments map[string]string  `yaml:"fragments,omitempty"`
}

// LocalizedScreen contains screen text and button label overrides for a locale.
type LocalizedScreen struct {
	ID          string            `yaml:"id"`
	Text        string            `yaml:"text,omitempty"`
	ButtonTexts map[string]string `yaml:"button_texts,omitempty"`
}

// LocalizedCommand contains command description overrides for a locale.
type LocalizedCommand struct {
	Command     string `yaml:"command"`
	Description string `yaml:"description,omitempty"`
}

// NewContentRepository creates and initializes a new content repository
func NewContentRepository(contentDir string) (*ContentRepository, error) {
	repo := &ContentRepository{
		catalog:    domain.NewContentCatalog(),
		screens:    make(map[string]*domain.Screen),
		navigation: domain.NewNavigationRegistry(),
	}

	// Load content first
	if err := repo.loadContent(contentDir); err != nil {
		return nil, err
	}

	repo.screens = repo.materializeScreens()
	repo.renderer = NewLocalizedContentRenderer(repo.catalog, repo.navigation)

	// Automatically build navigation hierarchy from screen relationships
	repo.navigation.BuildFromScreens(repo.screens)

	return repo, nil
}

// loadContent loads all content files from the specified directory
func (r *ContentRepository) loadContent(contentDir string) error {
	if err := r.loadSharedContent(contentDir); err != nil {
		return err
	}

	if err := r.loadLocalizedContent(filepath.Join(contentDir, "locales")); err != nil {
		return err
	}

	return r.validateLoadedCatalog()
}

func (r *ContentRepository) loadSharedContent(contentDir string) error {
	entries, err := os.ReadDir(contentDir)
	if err != nil {
		return fmt.Errorf("failed to read content directory: %w", err)
	}

	if err := r.loadAllFiles(contentDir, entries); err != nil {
		return err
	}

	return nil
}

// loadAllFiles loads all YAML files from the directory
func (r *ContentRepository) loadAllFiles(contentDir string, entries []os.DirEntry) error {
	for _, entry := range entries {
		if !r.isYAMLFile(entry) || r.isLocaleDir(entry) {
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

func (r *ContentRepository) isLocaleDir(entry os.DirEntry) bool {
	return entry.IsDir() && entry.Name() == "locales"
}

func (r *ContentRepository) loadLocalizedContent(localesDir string) error {
	entries, err := os.ReadDir(localesDir)
	if err != nil {
		if os.IsNotExist(err) {
			return nil
		}
		return fmt.Errorf("failed to read locales directory: %w", err)
	}

	for _, entry := range entries {
		if !entry.IsDir() {
			continue
		}

		localeCode := locale.Normalize(entry.Name())
		if err := r.loadLocaleDirectory(filepath.Join(localesDir, entry.Name()), localeCode); err != nil {
			return err
		}
	}

	return nil
}

func (r *ContentRepository) loadLocaleDirectory(localeDir string, localeCode string) error {
	entries, err := os.ReadDir(localeDir)
	if err != nil {
		return fmt.Errorf("failed to read locale directory %s: %w", localeDir, err)
	}

	for _, entry := range entries {
		if !r.isYAMLFile(entry) {
			continue
		}

		filePath := filepath.Join(localeDir, entry.Name())
		if err := r.loadLocalizedContentFile(filePath, localeCode); err != nil {
			return fmt.Errorf("failed to load localized %s: %w", entry.Name(), err)
		}
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

	return r.registerCommands(content.Commands)
}

func (r *ContentRepository) loadLocalizedContentFile(path string, localeCode string) error {
	data, err := os.ReadFile(path)
	if err != nil {
		return err
	}

	content, err := r.parseLocalizedContentFile(data)
	if err != nil {
		return err
	}

	if err := r.applyLocalizedScreens(content.Screens, localeCode, path); err != nil {
		return err
	}

	if err := r.applyLocalizedCommands(content.Commands, localeCode, path); err != nil {
		return err
	}

	return r.applyLocalizedFragments(content.Fragments, localeCode)
}

// parseContentFile parses YAML content into ContentFile structure
func (r *ContentRepository) parseContentFile(data []byte) (*ContentFile, error) {
	var content ContentFile
	if err := yaml.Unmarshal(data, &content); err != nil {
		return nil, fmt.Errorf("failed to parse content: %w", err)
	}
	return &content, nil
}

func (r *ContentRepository) parseLocalizedContentFile(data []byte) (*LocaleContentFile, error) {
	var content LocaleContentFile
	if err := yaml.Unmarshal(data, &content); err != nil {
		return nil, fmt.Errorf("failed to parse localized content: %w", err)
	}
	return &content, nil
}

// registerScreens registers all screens from a content file
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

// registerCommands registers all commands from a content file.
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

// GetScreenForLocale returns a screen by ID localized for the given Telegram locale.
func (r *ContentRepository) GetScreenForLocale(screenID, localeCode string) (*domain.Screen, error) {
	return r.renderer.Screen(screenID, localeCode, r.screens)
}

// GetCommandsForLocale returns the command registry localized for the given Telegram locale.
func (r *ContentRepository) GetCommandsForLocale(localeCode string) *domain.CommandRegistry {
	return r.renderer.Commands(localeCode)
}

// GetAllScreens returns all registered screens
func (r *ContentRepository) GetAllScreens() map[string]*domain.Screen {
	return r.screens
}

// GetNavigationRegistry returns the navigation registry (for debugging/inspection)
func (r *ContentRepository) GetNavigationRegistry() *domain.NavigationRegistry {
	return r.navigation
}
