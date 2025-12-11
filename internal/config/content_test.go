package config

import (
	"os"
	"path/filepath"
	"testing"
)

func TestLoadContentFile(t *testing.T) {
	// Create temporary directory
	tmpDir, err := os.MkdirTemp("", "content-test-*")
	if err != nil {
		t.Fatalf("Failed to create temp dir: %v", err)
	}
	defer os.RemoveAll(tmpDir)

	// Create test YAML file
	testYAML := `version: "1.0"
screens:
  - id: "TEST_SCREEN"
    text: "Test message"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows:
        - buttons:
            - id: "test_btn"
              text: "Test Button"
              type: "callback"
              callback: "TEST_CALLBACK"
    navigation:
      - callback: "TEST_CALLBACK"
        target: "TEST_SCREEN"
`

	testFile := filepath.Join(tmpDir, "test.yaml")
	if err := os.WriteFile(testFile, []byte(testYAML), 0644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	// Load content
	repo, err := NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to load content: %v", err)
	}

	// Test screen retrieval
	screen, err := repo.GetScreen("TEST_SCREEN")
	if err != nil {
		t.Fatalf("Failed to get screen: %v", err)
	}

	if screen.ID != "TEST_SCREEN" {
		t.Errorf("Expected screen ID 'TEST_SCREEN', got '%s'", screen.ID)
	}

	if screen.Text != "Test message" {
		t.Errorf("Expected text 'Test message', got '%s'", screen.Text)
	}

	if len(screen.InlineKeyboard.Rows) != 1 {
		t.Errorf("Expected 1 button row, got %d", len(screen.InlineKeyboard.Rows))
	}
}

func TestGetNonExistentScreen(t *testing.T) {
	tmpDir, err := os.MkdirTemp("", "content-test-*")
	if err != nil {
		t.Fatalf("Failed to create temp dir: %v", err)
	}
	defer os.RemoveAll(tmpDir)

	repo, err := NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to create repository: %v", err)
	}

	_, err = repo.GetScreen("NON_EXISTENT")
	if err == nil {
		t.Error("Expected error for non-existent screen, got nil")
	}
}

func TestDuplicateScreenID(t *testing.T) {
	tmpDir, err := os.MkdirTemp("", "content-test-*")
	if err != nil {
		t.Fatalf("Failed to create temp dir: %v", err)
	}
	defer os.RemoveAll(tmpDir)

	// Create YAML with duplicate screen ID
	testYAML := `version: "1.0"
screens:
  - id: "DUPLICATE"
    text: "First"
  - id: "DUPLICATE"
    text: "Second"
`

	testFile := filepath.Join(tmpDir, "test.yaml")
	if err := os.WriteFile(testFile, []byte(testYAML), 0644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	// Should fail due to duplicate
	_, err = NewContentRepository(tmpDir)
	if err == nil {
		t.Error("Expected error for duplicate screen ID, got nil")
	}
}
