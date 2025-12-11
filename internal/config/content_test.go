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

	// Create test YAML file with MAIN_MENU and a child screen
	testYAML := `version: "1.0"
screens:
  - id: "MAIN_MENU"
    text: "Main Menu"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows:
        - buttons:
            - id: "test_btn"
              text: "🧪 Test Screen"
              type: "callback"
              callback: "TEST_SCREEN_BUTTON"
    navigation:
      - callback: "TEST_SCREEN_BUTTON"
        target: "TEST_SCREEN"
  
  - id: "TEST_SCREEN"
    text: "Test message"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows:
        - buttons:
            - id: "content_btn"
              text: "Content Button"
              type: "callback"
              callback: "CONTENT_CALLBACK"
`

	testFile := filepath.Join(tmpDir, "test.yaml")
	if err := os.WriteFile(testFile, []byte(testYAML), 0644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	// Load content (navigation builds automatically)
	repo, err := NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to load content: %v", err)
	}

	// Test main menu screen retrieval (no navigation added)
	mainMenu, err := repo.GetScreen("MAIN_MENU")
	if err != nil {
		t.Fatalf("Failed to get main menu: %v", err)
	}

	if mainMenu.ID != "MAIN_MENU" {
		t.Errorf("Expected screen ID 'MAIN_MENU', got '%s'", mainMenu.ID)
	}

	// Main menu should have only original buttons (no auto-navigation)
	if len(mainMenu.InlineKeyboard.Rows) != 1 {
		t.Errorf("Expected 1 button row for MAIN_MENU, got %d", len(mainMenu.InlineKeyboard.Rows))
	}

	// Test child screen retrieval (should have auto-navigation)
	screen, err := repo.GetScreen("TEST_SCREEN")
	if err != nil {
		t.Fatalf("Failed to get test screen: %v", err)
	}

	if screen.ID != "TEST_SCREEN" {
		t.Errorf("Expected screen ID 'TEST_SCREEN', got '%s'", screen.ID)
	}

	if screen.Text != "Test message" {
		t.Errorf("Expected text 'Test message', got '%s'", screen.Text)
	}

	// Should have original row + auto-navigation row
	// Since parent is MAIN_MENU (detected automatically), we only get main menu button
	if len(screen.InlineKeyboard.Rows) != 2 {
		t.Errorf("Expected 2 button rows (1 original + 1 auto-nav), got %d", len(screen.InlineKeyboard.Rows))
	}

	// Check that auto-navigation was added
	lastRow := screen.InlineKeyboard.Rows[len(screen.InlineKeyboard.Rows)-1]
	// Should only have 1 button (main menu) since parent is MAIN_MENU
	if len(lastRow.Buttons) != 1 {
		t.Errorf("Expected 1 navigation button (main menu only), got %d", len(lastRow.Buttons))
	}

	if lastRow.Buttons[0].Text != "🏠 В главное меню" {
		t.Errorf("Expected main menu button, got '%s'", lastRow.Buttons[0].Text)
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
