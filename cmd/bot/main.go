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
	cfg := loadConfig()
	setupLogging(cfg.Debug)

	slog.Info("EKPA Antalya Bot starting")

	botService := createBotService(cfg)
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()

	runBot(ctx, cancel, botService)
}

// loadConfig loads and validates configuration
func loadConfig() *config.Config {
	cfg, err := config.Load()
	if err != nil {
		slog.Error("Failed to load configuration", "error", err)
		os.Exit(1)
	}
	return cfg
}

// createBotService creates the bot service or exits on error
func createBotService(cfg *config.Config) *service.BotService {
	botService, err := service.NewBotService(cfg)
	if err != nil {
		slog.Error("Failed to create bot service", "error", err)
		os.Exit(1)
	}
	return botService
}

// runBot starts the bot and handles shutdown
func runBot(ctx context.Context, cancel context.CancelFunc, botService *service.BotService) {
	sigChan := setupSignalHandler()
	errChan := startBotAsync(ctx, botService)

	waitForShutdown(ctx, cancel, sigChan, errChan, botService)
	slog.Info("Bot stopped gracefully")
}

// setupSignalHandler sets up OS signal handling
func setupSignalHandler() chan os.Signal {
	sigChan := make(chan os.Signal, 1)
	signal.Notify(sigChan, os.Interrupt, syscall.SIGTERM)
	return sigChan
}

// startBotAsync starts the bot in a goroutine
func startBotAsync(ctx context.Context, botService *service.BotService) chan error {
	errChan := make(chan error, 1)
	go func() {
		if err := botService.Start(ctx); err != nil {
			errChan <- err
		}
	}()
	return errChan
}

// waitForShutdown waits for shutdown signal or error and handles cleanup
func waitForShutdown(ctx context.Context, cancel context.CancelFunc, sigChan chan os.Signal, errChan chan error, botService *service.BotService) {
	select {
	case <-sigChan:
		handleGracefulShutdown(ctx, cancel, botService)
	case err := <-errChan:
		handleBotError(err)
	}
}

// handleGracefulShutdown performs graceful shutdown
func handleGracefulShutdown(ctx context.Context, cancel context.CancelFunc, botService *service.BotService) {
	slog.Info("Received shutdown signal")
	cancel()

	shutdownCtx, shutdownCancel := context.WithTimeout(context.WithoutCancel(ctx), 5*time.Second)
	defer shutdownCancel()

	if err := botService.Stop(shutdownCtx); err != nil {
		slog.Error("Error during shutdown", "error", err)
	}
}

// handleBotError handles bot errors
func handleBotError(err error) {
	slog.Error("Bot error", "error", err)
	os.Exit(1)
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
