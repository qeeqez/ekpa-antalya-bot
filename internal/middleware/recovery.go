package middleware

import (
	"log/slog"
	"runtime/debug"
)

// RecoverPanic recovers from panics and logs the error
func RecoverPanic() {
	if r := recover(); r != nil {
		slog.Error("Recovered from panic", "panic", r, "stack", string(debug.Stack()))
	}
}
