package config_test

import (
	"os"
	"path/filepath"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
)

func TestDuplicateScreenID(t *testing.T) {
	tmpDir := t.TempDir()
	writeRequiredLocaleBundles(t, tmpDir)

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
	_, err := config.NewContentRepository(tmpDir)
	if err == nil {
		t.Error("Expected error for duplicate screen ID, got nil")
	}
}
