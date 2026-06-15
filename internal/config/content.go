package config

import (
	"fmt"
	"os"
	"path/filepath"
	"sync"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
	"gopkg.in/yaml.v3"
)

// ContentRepository manages all bot content (screens, commands, etc.)
type ContentRepository struct {
	mu         sync.RWMutex
	contentDir string
	catalog    *domain.ContentCatalog
	screens    map[string]*domain.Screen
	navigation *domain.NavigationRegistry
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
	repo, err := loadContentRepository(contentDir)
	if err != nil {
		return nil, err
	}

	return repo, nil
}

func loadContentRepository(contentDir string) (*ContentRepository, error) {
	repo := &ContentRepository{
		contentDir: contentDir,
		catalog:    domain.NewContentCatalog(),
		screens:    make(map[string]*domain.Screen),
		navigation: domain.NewNavigationRegistry(),
	}

	if err := repo.loadContent(contentDir); err != nil {
		return nil, err
	}

	repo.screens = repo.materializeScreens()
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

// GetScreenForLocale returns a screen by ID localized for the given Telegram locale.
func (r *ContentRepository) GetScreenForLocale(screenID, localeCode string) (*domain.Screen, error) {
	r.mu.RLock()
	defer r.mu.RUnlock()

	renderer := NewLocalizedContentRenderer(r.catalog, r.navigation)
	return renderer.Screen(screenID, localeCode, r.screens)
}

// GetCommandsForLocale returns the command registry localized for the given Telegram locale.
func (r *ContentRepository) GetCommandsForLocale(localeCode string) *domain.CommandRegistry {
	r.mu.RLock()
	defer r.mu.RUnlock()

	renderer := NewLocalizedContentRenderer(r.catalog, r.navigation)
	return renderer.Commands(localeCode)
}

// GetAllScreens returns all registered screens
func (r *ContentRepository) GetAllScreens() map[string]*domain.Screen {
	r.mu.RLock()
	defer r.mu.RUnlock()

	return r.screens
}

// GetNavigationRegistry returns the navigation registry (for debugging/inspection)
func (r *ContentRepository) GetNavigationRegistry() *domain.NavigationRegistry {
	r.mu.RLock()
	defer r.mu.RUnlock()

	return r.navigation
}
