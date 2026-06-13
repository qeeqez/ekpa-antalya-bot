package service_test

import (
	"bytes"
	"log/slog"
	"strings"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/service"
)

func TestTelegoLoggerEmitsStructuredLogs(t *testing.T) {
	var buf bytes.Buffer
	previous := slog.Default()
	slog.SetDefault(slog.New(slog.NewJSONHandler(&buf, nil)))
	t.Cleanup(func() {
		slog.SetDefault(previous)
	})

	logger := service.NewTelegoLogger(false)
	logger.Errorf("Getting updates: %s", "lookup api.telegram.org: i/o timeout")

	got := buf.String()
	if !strings.Contains(got, "\"msg\":\"Telegram client error\"") {
		t.Fatalf("expected structured slog message, got %q", got)
	}
	if !strings.Contains(got, "\"component\":\"telego\"") {
		t.Fatalf("expected component field in log, got %q", got)
	}
	if !strings.Contains(got, "\"details\":\"Getting updates: lookup api.telegram.org: i/o timeout\"") {
		t.Fatalf("expected details field in log, got %q", got)
	}
}
