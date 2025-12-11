package main

import (
	"context"
	"log"
	"os"
	"os/signal"
	"syscall"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/service"
)

func main() {

	// Load configuration from environment variables
	cfg, err := config.Load()
	if err != nil {
		log.Fatalf("Failed to load configuration: %v", err)
	}

	// Create bot service
	botService, err := service.NewBotService(cfg)
	if err != nil {
		log.Fatalf("Failed to create bot service: %v", err)
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
		log.Println("Received shutdown signal")
		cancel()
		botService.Stop()
	case err := <-errChan:
		log.Fatalf("Bot error: %v", err)
	}

	log.Println("Bot stopped gracefully")
}
