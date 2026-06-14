package config_test

import (
	"path/filepath"
	"runtime"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
)

func TestLocaleParityAuditIsClean(t *testing.T) {
	_, file, _, ok := runtime.Caller(0)
	if !ok {
		t.Fatal("failed to resolve test file location")
	}

	repo, err := config.NewContentRepository(filepath.Join(filepath.Dir(file), "..", "..", "content"))
	if err != nil {
		t.Fatalf("failed to load content: %v", err)
	}

	issues := repo.AuditLocaleParity()
	if len(issues) != 0 {
		t.Fatalf("expected no locale parity issues, got %d: %v", len(issues), issues)
	}
}
