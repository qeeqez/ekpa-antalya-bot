package config

import (
	"fmt"
	"os"
	"path/filepath"

	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"gopkg.in/yaml.v3"
)

// ContentRepository manages all bot content (screens, commands, etc.)
type ContentRepository struct {
	screens  map[string]*domain.Screen
	commands *domain.CommandRegistry
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
	}

	if err := repo.loadContent(contentDir); err != nil {
		return nil, err
	}

	return repo, nil
}

// loadContent loads all content files from the specified directory
func (r *ContentRepository) loadContent(contentDir string) error {
	entries, err := os.ReadDir(contentDir)
	if err != nil {
		return fmt.Errorf("failed to read content directory: %w", err)
	}

	for _, entry := range entries {
		if entry.IsDir() || filepath.Ext(entry.Name()) != ".yaml" {
			continue
		}

		filePath := filepath.Join(contentDir, entry.Name())
		if err := r.loadContentFile(filePath); err != nil {
			return fmt.Errorf("failed to load %s: %w", entry.Name(), err)
		}
	}

	// Validate all screens
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

	var content ContentFile
	if err := yaml.Unmarshal(data, &content); err != nil {
		return fmt.Errorf("failed to parse content: %w", err)
	}

	// Register screens
	for i := range content.Screens {
		screen := &content.Screens[i]
		if _, exists := r.screens[screen.ID]; exists {
			return fmt.Errorf("duplicate screen ID: %s", screen.ID)
		}
		r.screens[screen.ID] = screen
	}

	// Register commands
	r.commands.Commands = append(r.commands.Commands, content.Commands...)

	return nil
}

// GetScreen returns a screen by ID
func (r *ContentRepository) GetScreen(screenID string) (*domain.Screen, error) {
	screen, ok := r.screens[screenID]
	if !ok {
		return nil, domain.ErrScreenNotFound(screenID)
	}
	return screen, nil
}

// GetCommands returns the command registry
func (r *ContentRepository) GetCommands() *domain.CommandRegistry {
	return r.commands
}

// GetAllScreens returns all registered screens
func (r *ContentRepository) GetAllScreens() map[string]*domain.Screen {
	return r.screens
}
