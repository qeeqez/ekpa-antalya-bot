package config_test

import (
	"os"
	"path/filepath"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

const (
	testMainMenuID           = "MAIN_MENU"
	testScreenID             = "TEST_SCREEN"
	testButtonID             = "test_btn"
	testButtonText           = "🧪 Test Screen"
	testButtonTextEnglish    = "🧪 Test Screen EN"
	testScreenText           = "Test message"
	testScreenTextEnglish    = "Test message EN"
	testCommandDescription   = "Главное меню"
	testCommandDescriptionEN = "Main menu"
	testContentButton        = "content_btn"
)

func writeLocaleFragmentFile(t *testing.T, dir string, localeCode string) {
	t.Helper()

	localeDir := filepath.Join(dir, "locales", localeCode)
	if err := os.MkdirAll(localeDir, 0o755); err != nil {
		t.Fatalf("Failed to create locale dir %s: %v", localeCode, err)
	}

	content := `version: "1.0"
fragments:
  button_main_menu: "` + localeCode + ` main menu"
`
	if err := os.WriteFile(filepath.Join(localeDir, "fragments.yaml"), []byte(content), 0o644); err != nil {
		t.Fatalf("Failed to write locale fragment file for %s: %v", localeCode, err)
	}
}

func writeLocaleBundles(t *testing.T, dir string, localeCodes ...string) {
	t.Helper()

	for _, localeCode := range localeCodes {
		writeLocaleFragmentFile(t, dir, localeCode)
	}
}

func writeRequiredLocaleBundles(t *testing.T, dir string) {
	t.Helper()

	writeLocaleBundles(t, dir, locale.SupportedLocales...)
}

func TestLoadContentFile(t *testing.T) {
	tmpDir := t.TempDir()

	// Create shared test YAML file with MAIN_MENU and a child screen
	testYAML := `version: "1.0"
commands:
  - command: "/start"
    description: "` + testCommandDescription + `"
    screen_id: "` + testMainMenuID + `"
screens:
  - id: "` + testMainMenuID + `"
    text: "Main Menu"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows:
        - buttons:
            - id: "` + testButtonID + `"
              text: "` + testButtonText + `"
              type: "callback"
              callback: "TEST_SCREEN_BUTTON"
    navigation:
      - callback: "TEST_SCREEN_BUTTON"
        target: "` + testScreenID + `"
  
  - id: "` + testScreenID + `"
    text: "` + testScreenText + `"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows:
        - buttons:
            - id: "` + testContentButton + `"
              text: "Content Button"
              type: "callback"
              callback: "CONTENT_CALLBACK"
`

	testFile := filepath.Join(tmpDir, "test.yaml")
	if err := os.WriteFile(testFile, []byte(testYAML), 0644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	writeRequiredLocaleBundles(t, tmpDir)

	enLocaleDir := filepath.Join(tmpDir, "locales", "en")

	enLocaleYAML := `version: "1.0"
commands:
  - command: "/start"
    description: "` + testCommandDescriptionEN + `"
screens:
  - id: "` + testMainMenuID + `"
    text: "*Main menu*"
    button_texts:
      ` + testButtonID + `: "` + testButtonTextEnglish + `"
  - id: "` + testScreenID + `"
    text: "` + testScreenTextEnglish + `"
    button_texts:
      ` + testContentButton + `: "Content Button EN"
`
	if err := os.WriteFile(filepath.Join(enLocaleDir, "test.yaml"), []byte(enLocaleYAML), 0o644); err != nil {
		t.Fatalf("Failed to write locale test file: %v", err)
	}

	// Load content (navigation builds automatically)
	repo, err := config.NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to load content: %v", err)
	}

	// Test main menu screen retrieval (no navigation added)
	mainMenu, err := repo.GetScreenForLocale(testMainMenuID, "ru")
	if err != nil {
		t.Fatalf("Failed to get main menu: %v", err)
	}

	if mainMenu.ID != testMainMenuID {
		t.Errorf("Expected screen ID '%s', got '%s'", testMainMenuID, mainMenu.ID)
	}

	// Main menu should have only original buttons (no auto-navigation)
	if len(mainMenu.InlineKeyboard.Rows) != 1 {
		t.Errorf("Expected 1 button row for MAIN_MENU, got %d", len(mainMenu.InlineKeyboard.Rows))
	}

	localizedMainMenu, err := repo.GetScreenForLocale(testMainMenuID, "en-GB")
	if err != nil {
		t.Fatalf("Failed to get localized main menu: %v", err)
	}

	if localizedMainMenu.Text != "*Main menu*" {
		t.Fatalf("Expected localized main menu text %q, got %q", "*Main menu*", localizedMainMenu.Text)
	}

	if got := localizedMainMenu.InlineKeyboard.Rows[0].Buttons[0].Text; got != testButtonTextEnglish {
		t.Fatalf("Expected localized main menu button text %q, got %q", testButtonTextEnglish, got)
	}

	// Test child screen retrieval (should have auto-navigation)
	screen, err := repo.GetScreenForLocale(testScreenID, "ru")
	if err != nil {
		t.Fatalf("Failed to get test screen: %v", err)
	}

	if screen.ID != testScreenID {
		t.Errorf("Expected screen ID '%s', got '%s'", testScreenID, screen.ID)
	}

	if screen.Text != testScreenText {
		t.Errorf("Expected text '%s', got '%s'", testScreenText, screen.Text)
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

	localizedScreen, err := repo.GetScreenForLocale(testScreenID, "en-GB")
	if err != nil {
		t.Fatalf("Failed to get localized screen: %v", err)
	}

	if localizedScreen.Text != testScreenTextEnglish {
		t.Fatalf("Expected localized text %q, got %q", testScreenTextEnglish, localizedScreen.Text)
	}

	if got := localizedScreen.InlineKeyboard.Rows[0].Buttons[0].Text; got != "Content Button EN" {
		t.Fatalf("Expected localized button text %q, got %q", "Content Button EN", got)
	}

	localizedCommands := repo.GetCommandsForLocale("en")
	cmd, found := localizedCommands.GetCommand("/start")
	if !found {
		t.Fatal("Expected /start command to be present")
	}

	if cmd.Description != testCommandDescriptionEN {
		t.Fatalf("Expected localized command description %q, got %q", testCommandDescriptionEN, cmd.Description)
	}

	fallbackScreen, err := repo.GetScreenForLocale(testScreenID, "de-DE")
	if err != nil {
		t.Fatalf("Failed to get fallback screen: %v", err)
	}

	if fallbackScreen.Text != testScreenText {
		t.Fatalf("Expected fallback text %q, got %q", testScreenText, fallbackScreen.Text)
	}
}

func TestScreenRTL(t *testing.T) {
	tmpDir := t.TempDir()

	testYAML := `version: "1.0"
screens:
  - id: "MAIN_MENU"
    text: "Main Menu"
    parse_mode: "RichHTML"
`
	testFile := filepath.Join(tmpDir, "test.yaml")
	if err := os.WriteFile(testFile, []byte(testYAML), 0o644); err != nil {
		t.Fatalf("Failed to write test file: %v", err)
	}

	writeRequiredLocaleBundles(t, tmpDir)

	repo, err := config.NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to load content: %v", err)
	}

	arScreen, err := repo.GetScreenForLocale("MAIN_MENU", "ar-EG")
	if err != nil {
		t.Fatalf("Failed to get Arabic screen: %v", err)
	}
	if !arScreen.IsRTL {
		t.Fatalf("Expected Arabic screen to be RTL")
	}

	ruScreen, err := repo.GetScreenForLocale("MAIN_MENU", "ru")
	if err != nil {
		t.Fatalf("Failed to get Russian screen: %v", err)
	}
	if ruScreen.IsRTL {
		t.Fatalf("Expected Russian screen not to be RTL")
	}
}

func TestGetNonExistentScreen(t *testing.T) {
	tmpDir := t.TempDir()
	writeRequiredLocaleBundles(t, tmpDir)

	repo, err := config.NewContentRepository(tmpDir)
	if err != nil {
		t.Fatalf("Failed to create repository: %v", err)
	}

	_, err = repo.GetScreenForLocale("NON_EXISTENT", "ru")
	if err == nil {
		t.Error("Expected error for non-existent screen, got nil")
	}
}
