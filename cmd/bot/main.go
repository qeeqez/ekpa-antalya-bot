package main

import (
	"context"
	"log/slog"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/service"
)

func main() {
	// Load configuration from environment variables
	cfg, err := config.Load()
	if err != nil {
		slog.Error("Failed to load configuration", "error", err)
		os.Exit(1)
	}

	// Setup structured logging
	setupLogging(cfg.Debug)

	slog.Info("EKPA Antalya Bot starting")

	// Create bot service
	botService, err := service.NewBotService(cfg)
	if err != nil {
		slog.Error("Failed to create bot service", "error", err)
		os.Exit(1)
	}

	// Create context with cancellation
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()

	// Handle shutdown signals
	sigChan := make(chan os.Signal, 1)
	signal.Notify(sigChan, os.Interrupt, syscall.SIGTERM)

	// Start bot in goroutine
	errChan := make(chan error, 1)
	go func() {
		if err := botService.Start(ctx); err != nil {
			errChan <- err
		}
	}()

	// Wait for shutdown signal or error
	select {
	case <-sigChan:
		slog.Info("Received shutdown signal")
		cancel()

		// Create shutdown context with timeout
		shutdownCtx, shutdownCancel := context.WithTimeout(context.Background(), 5*time.Second)
		defer shutdownCancel()

		// Stop bot gracefully
		if err := botService.Stop(shutdownCtx); err != nil {
			slog.Error("Error during shutdown", "error", err)
		}
	case err := <-errChan:
		slog.Error("Bot error", "error", err)
		os.Exit(1)
	}

	slog.Info("Bot stopped gracefully")
}

// setupLogging configures structured logging based on debug mode
func setupLogging(debug bool) {
	var handler slog.Handler

	if debug {
		// Development: text format with debug level
		handler = slog.NewTextHandler(os.Stdout, &slog.HandlerOptions{
			Level: slog.LevelDebug,
		})
	} else {
		// Production: JSON format with info level
		handler = slog.NewJSONHandler(os.Stdout, &slog.HandlerOptions{
			Level: slog.LevelInfo,
		})
	}

	logger := slog.New(handler)
	slog.SetDefault(logger)
}
