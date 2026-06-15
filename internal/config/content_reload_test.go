package config_test

import (
	"os"
	"path/filepath"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
)

func TestContentRepositoryReload(t *testing.T) {
	tmpDir := t.TempDir()
	writeRequiredLocaleBundles(t, tmpDir)

	testYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "First version"
    inline_keyboard:
      rows: []
`
	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	repo, err := config.NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to load content: %v", err)
	}

	screen, err := repo.GetScreenForLocale(testMainMenuID, "ru")
	if err != nil {
		t.Fatalf("Failed to load screen: %v", err)
	}

	if screen.Text != "First version" {
		t.Fatalf("Expected initial screen text %q, got %q", "First version", screen.Text)
	}

	updatedYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "Second version"
    inline_keyboard:
      rows: []
`
	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(updatedYAML), 0o644); err != nil {
		t.Fatalf("Failed to update test file: %v", err)
	}

	result, err := repo.Reload()
	if err != nil {
		t.Fatalf("Failed to reload content: %v", err)
	}

	if result.CommandsChanged {
		t.Fatal("Expected command reload to stay unchanged for screen-only update")
	}

	updatedScreen, err := repo.GetScreenForLocale(testMainMenuID, "ru")
	if err != nil {
		t.Fatalf("Failed to load reloaded screen: %v", err)
	}

	if updatedScreen.Text != "Second version" {
		t.Fatalf("Expected reloaded screen text %q, got %q", "Second version", updatedScreen.Text)
	}
}

func TestContentRepositoryReloadDetectsCommandChanges(t *testing.T) {
	tmpDir := t.TempDir()
	writeRequiredLocaleBundles(t, tmpDir)

	testYAML := `version: "1.0"
commands:
  - command: "/start"
    screen_id: "` + testMainMenuID + `"
screens:
  - id: "` + testMainMenuID + `"
    text: "Menu"
    inline_keyboard:
      rows: []
`
	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	repo, err := config.NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to load content: %v", err)
	}

	enLocaleDir := filepath.Join(tmpDir, "locales", "en")
	updatedLocale := `version: "1.0"
commands:
  - command: "/start"
    description: "Second description"
`
	if err := os.WriteFile(filepath.Join(enLocaleDir, "test.yaml"), []byte(updatedLocale), 0o644); err != nil {
		t.Fatalf("Failed to update locale test file: %v", err)
	}

	result, err := repo.Reload()
	if err != nil {
		t.Fatalf("Failed to reload content: %v", err)
	}

	if !result.CommandsChanged {
		t.Fatal("Expected command reload to be detected")
	}
}
