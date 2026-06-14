package config

import (
	"errors"
	"fmt"
	"maps"
	"os"
	"path/filepath"
	"strings"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
	"gopkg.in/yaml.v3"
)

// ContentRepository manages all bot content (screens, commands, etc.)
type ContentRepository struct {
	catalog    *domain.ContentCatalog
	navigation *domain.NavigationRegistry
}

// ContentFile represents a content YAML file structure
type ContentFile struct {
	Version  string           `yaml:"version"`
	Screens  []domain.Screen  `yaml:"screens"`
	Commands []domain.Command `yaml:"commands,omitempty"`
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
		navigation: domain.NewNavigationRegistry(),
	}

	// Load content first
	if err := repo.loadContent(contentDir); err != nil {
		return nil, err
	}

	// Automatically build navigation hierarchy from screen relationships
	repo.navigation.BuildFromScreens(repo.catalog.Screens)

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

	return r.validateMergedScreens()
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
func (r *ContentRepository) registerScreens(screens []domain.Screen) error {
	for i := range screens {
		screen := &screens[i]
		if _, exists := r.catalog.Screens[screen.ID]; exists {
			return fmt.Errorf("duplicate screen ID: %s", screen.ID)
		}
		r.catalog.Screens[screen.ID] = screen
	}
	return nil
}

func (r *ContentRepository) validateSharedScreen(screen *domain.Screen) error {
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
func (r *ContentRepository) registerCommands(commands []domain.Command) error {
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

func (r *ContentRepository) applyLocalizedScreens(screens []LocalizedScreen, localeCode, path string) error {
	for _, localized := range screens {
		base, ok := r.catalog.Screens[localized.ID]
		if !ok {
			return fmt.Errorf("localized screen %s in %s has no shared base screen", localized.ID, path)
		}

		if base.Locales == nil {
			base.Locales = make(map[string]domain.ScreenLocale)
		}

		base.Locales[localeCode] = domain.ScreenLocale{
			Text:        localized.Text,
			ButtonTexts: cloneStringMap(localized.ButtonTexts),
		}
	}

	return nil
}

func (r *ContentRepository) applyLocalizedCommands(commands []LocalizedCommand, localeCode, path string) error {
	for _, localized := range commands {
		base, found := r.catalog.Commands[localized.Command]
		if !found {
			return fmt.Errorf("localized command %s in %s has no shared base command", localized.Command, path)
		}

		if base.Locales == nil {
			base.Locales = make(map[string]domain.CommandLocale)
		}

		base.Locales[localeCode] = domain.CommandLocale{
			Description: localized.Description,
		}
	}

	return nil
}

func (r *ContentRepository) applyLocalizedFragments(fragments map[string]string, localeCode string) error {
	if len(fragments) == 0 {
		return nil
	}

	normalized := locale.Normalize(localeCode)
	if r.catalog.Fragments[normalized] == nil {
		r.catalog.Fragments[normalized] = make(map[string]string, len(fragments))
	}
	maps.Copy(r.catalog.Fragments[normalized], fragments)

	return nil
}

// GetScreen returns a screen by ID with automatic navigation buttons
func (r *ContentRepository) GetScreen(screenID string) (*domain.Screen, error) {
	return r.GetScreenForLocale(screenID, locale.DefaultLocale)
}

// GetScreenForLocale returns a screen by ID localized for the given Telegram locale.
func (r *ContentRepository) GetScreenForLocale(screenID, localeCode string) (*domain.Screen, error) {
	screen, ok := r.catalog.Screens[screenID]
	if !ok {
		return nil, domain.ErrScreenNotFound(screenID)
	}

	enhancedScreen := r.cloneScreen(screen)
	r.applyScreenLocale(enhancedScreen, locale.DefaultLocale)
	if normalized := locale.Normalize(localeCode); normalized != locale.DefaultLocale {
		r.applyScreenLocale(enhancedScreen, normalized)
	}

	// Add automatic navigation buttons using localized labels.
	enhancedScreen = r.navigation.AddAutoNavigation(enhancedScreen, locale.Normalize(localeCode))
	r.expandScreenFragments(enhancedScreen, locale.Normalize(localeCode))
	if err := enhancedScreen.Validate(); err != nil {
		return nil, fmt.Errorf("invalid localized screen %s: %w", screenID, err)
	}
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
		Commands: make([]domain.Command, 0, len(r.catalog.CommandOrder)),
	}

	for _, name := range r.catalog.CommandOrder {
		cmd := r.catalog.Commands[name]
		localized := *cmd
		if localeData, ok := cmd.Locales[locale.DefaultLocale]; ok && localeData.Description != "" {
			localized.Description = localeData.Description
		}
		if normalized != locale.DefaultLocale {
			if localeData, ok := cmd.Locales[normalized]; ok && localeData.Description != "" {
				localized.Description = localeData.Description
			}
		}
		localized.Description = r.expandText(localized.Description, normalized)
		registry.Commands = append(registry.Commands, localized)
	}

	return registry
}

// GetAllScreens returns all registered screens
func (r *ContentRepository) GetAllScreens() map[string]*domain.Screen {
	return r.catalog.Screens
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

func cloneStringMap(values map[string]string) map[string]string {
	if len(values) == 0 {
		return nil
	}

	clone := make(map[string]string, len(values))
	maps.Copy(clone, values)

	return clone
}

func (r *ContentRepository) expandScreenFragments(screen *domain.Screen, localeCode string) {
	screen.Text = r.expandText(screen.Text, localeCode)
	for rowIdx := range screen.InlineKeyboard.Rows {
		for btnIdx := range screen.InlineKeyboard.Rows[rowIdx].Buttons {
			button := &screen.InlineKeyboard.Rows[rowIdx].Buttons[btnIdx]
			button.Text = r.expandText(button.Text, localeCode)
		}
	}
}

func (r *ContentRepository) expandText(text, localeCode string) string {
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

func (r *ContentRepository) replaceFragmentRefs(text, localeCode string) string {
	fragments := r.fragmentSet(localeCode)
	for key, value := range fragments {
		text = replaceFragment(text, key, value)
	}
	return text
}

func (r *ContentRepository) fragmentSet(localeCode string) map[string]string {
	normalized := locale.Normalize(localeCode)
	merged := make(map[string]string)
	if base, ok := r.catalog.Fragments[locale.DefaultLocale]; ok {
		maps.Copy(merged, base)
	}
	if normalized != locale.DefaultLocale {
		if overlay, ok := r.catalog.Fragments[normalized]; ok {
			maps.Copy(merged, overlay)
		}
	}
	return merged
}

func replaceFragment(text, key, value string) string {
	placeholder := "{{" + key + "}}"
	return strings.ReplaceAll(text, placeholder, value)
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
