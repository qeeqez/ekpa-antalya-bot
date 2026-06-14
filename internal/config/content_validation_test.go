package config_test

import (
	"os"
	"path/filepath"
	"strings"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
)

func TestMissingDefaultFragmentFails(t *testing.T) {
	tmpDir := t.TempDir()

	testYAML := `version: "1.0"
commands:
  - command: "/start"
    description: "Главное меню"
screens:
  - id: "` + testMainMenuID + `"
    text: "*{{missing_fragment}}*"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows: []
`

	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	writeRequiredLocaleBundles(t, tmpDir)

	_, err := config.NewContentRepository(tmpDir)
	if err == nil {
		t.Fatal("Expected error for missing default fragment, got nil")
	}
}

func TestMissingRequiredLocaleBundleFails(t *testing.T) {
	tmpDir := t.TempDir()

	testYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "Main menu"
    inline_keyboard:
      rows: []
`

	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	writeLocaleBundles(t, tmpDir, "ru", "en", "tr")

	_, err := config.NewContentRepository(tmpDir)
	if err == nil {
		t.Fatal("Expected error for missing required locale bundle, got nil")
	}
}

func TestMarkdownV2LocalizedTextRequiresEscaping(t *testing.T) {
	tmpDir := t.TempDir()

	testYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "*Hello*"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows: []
`
	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	writeRequiredLocaleBundles(t, tmpDir)

	enLocaleDir := filepath.Join(tmpDir, "locales", "en")
	unescapedYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "*Hello. EN*"
`
	if err := os.WriteFile(filepath.Join(enLocaleDir, "test.yaml"), []byte(unescapedYAML), 0o644); err != nil {
		t.Fatalf("Failed to write locale test file: %v", err)
	}

	_, err := config.NewContentRepository(tmpDir)
	if err == nil {
		t.Fatal("Expected MarkdownV2 validation error, got nil")
	}

	if !strings.Contains(err.Error(), "invalid MarkdownV2 text") {
		t.Fatalf("Expected MarkdownV2 validation error, got %v", err)
	}
}

func TestMarkdownV2LocalizedTextWithEscapesPasses(t *testing.T) {
	tmpDir := t.TempDir()

	testYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "*Hello*"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows: []
`
	if err := os.WriteFile(filepath.Join(tmpDir, "test.yaml"), []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	writeRequiredLocaleBundles(t, tmpDir)

	enLocaleDir := filepath.Join(tmpDir, "locales", "en")
	escapedYAML := `version: "1.0"
screens:
  - id: "` + testMainMenuID + `"
    text: "*Hello\\. EN*"
`
	if err := os.WriteFile(filepath.Join(enLocaleDir, "test.yaml"), []byte(escapedYAML), 0o644); err != nil {
		t.Fatalf("Failed to write locale test file: %v", err)
	}

	if _, err := config.NewContentRepository(tmpDir); err != nil {
		t.Fatalf("Expected MarkdownV2 validation to pass, got %v", err)
	}
}
