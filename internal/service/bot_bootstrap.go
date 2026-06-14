package service

import (
	"fmt"
	"log/slog"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/handler"
	"github.com/qeeqez/ekpaantalyabot/internal/health"
)

// NewBotService creates a new bot service.
func NewBotService(cfg *config.Config) (*BotService, error) {
	bot, err := createBot(cfg)
	if err != nil {
		return nil, err
	}

	healthChecker := health.NewChecker()

	content, err := loadContent(healthChecker)
	if err != nil {
		return nil, err
	}

	handlerChain := createHandlerChain(content, bot, cfg.Bot.Username)

	return &BotService{
		bot:     bot,
		content: content,
		handler: handlerChain,
		health:  healthChecker,
		config:  cfg,
	}, nil
}

// createBot creates a Telegram bot instance with optional debug logging.
func createBot(cfg *config.Config) (*telego.Bot, error) {
	opts := []telego.BotOption{
		telego.WithLogger(NewTelegoLogger(cfg.Debug)),
	}

	if cfg.Debug {
		slog.Info("Debug mode enabled - verbose logging active")
		opts = append(opts, telego.WithDebugMode())
	}

	bot, err := telego.NewBot(cfg.Bot.Token, opts...)
	if err != nil {
		return nil, fmt.Errorf("failed to create bot: %w", err)
	}

	return bot, nil
}

// loadContent loads content repository and records load event.
func loadContent(healthChecker *health.Checker) (*config.ContentRepository, error) {
	content, err := config.NewContentRepository("content")
	if err != nil {
		return nil, fmt.Errorf("failed to load content: %w", err)
	}
	healthChecker.RecordContentLoad()
	return content, nil
}

// createHandlerChain creates the handler chain with all handlers.
func createHandlerChain(content *config.ContentRepository, bot *telego.Bot, botUsername string) *handler.Chain {
	sender := NewMessageSender(bot)
	commandHandler := handler.NewCommandHandler(content, sender, botUsername)
	callbackHandler := handler.NewCallbackHandler(content, sender)
	return handler.NewChain(commandHandler, callbackHandler)
}
