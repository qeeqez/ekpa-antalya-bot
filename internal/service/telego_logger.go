package service

import (
	"fmt"
	"log/slog"

	"github.com/mymmrac/telego"
)

// slogTelegoLogger adapts telego's logger interface to slog.
type slogTelegoLogger struct {
	debug bool
}

// newTelegoLogger creates a telego logger backed by structured slog output.
func newTelegoLogger(debug bool) telego.Logger {
	return &slogTelegoLogger{debug: debug}
}

// Debugf logs telego debug output when debug mode is enabled.
func (l *slogTelegoLogger) Debugf(format string, args ...any) {
	if !l.debug {
		return
	}

	slog.Debug("Telegram client debug", "component", "telego", "details", fmt.Sprintf(format, args...))
}

// Errorf logs telego errors as structured warnings because long-polling retries are usually transient.
func (l *slogTelegoLogger) Errorf(format string, args ...any) {
	slog.Warn("Telegram client error", "component", "telego", "details", fmt.Sprintf(format, args...))
}
