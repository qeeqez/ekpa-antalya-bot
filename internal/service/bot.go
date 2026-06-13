package service

import (
	"context"
	"errors"
	"fmt"
	"log/slog"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/handler"
	"github.com/qeeqez/ekpaantalyabot/internal/health"
	"github.com/qeeqez/ekpaantalyabot/internal/middleware"
)

// BotService manages the Telegram bot lifecycle
type BotService struct {
	bot     *telego.Bot
	content *config.ContentRepository
	handler *handler.Chain
	health  *health.Checker
	config  *config.Config
}

// NewBotService creates a new bot service
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

// createBot creates a Telegram bot instance with optional debug logging
func createBot(cfg *config.Config) (*telego.Bot, error) {
	var bot *telego.Bot
	var err error

	opts := []telego.BotOption{
		telego.WithLogger(NewTelegoLogger(cfg.Debug)),
	}

	if cfg.Debug {
		slog.Info("Debug mode enabled - verbose logging active")
		opts = append(opts, telego.WithDebugMode())
	}

	bot, err = telego.NewBot(cfg.Bot.Token, opts...)

	if err != nil {
		return nil, fmt.Errorf("failed to create bot: %w", err)
	}

	return bot, nil
}

// loadContent loads content repository and records load event
func loadContent(healthChecker *health.Checker) (*config.ContentRepository, error) {
	content, err := config.NewContentRepository("content")
	if err != nil {
		return nil, fmt.Errorf("failed to load content: %w", err)
	}
	healthChecker.RecordContentLoad()
	return content, nil
}

// createHandlerChain creates the handler chain with all handlers
func createHandlerChain(content *config.ContentRepository, bot *telego.Bot, botUsername string) *handler.Chain {
	sender := NewMessageSender(bot)
	commandHandler := handler.NewCommandHandler(content, sender, botUsername)
	callbackHandler := handler.NewCallbackHandler(content, sender)
	return handler.NewChain(commandHandler, callbackHandler)
}

// Start starts the bot
func (s *BotService) Start(ctx context.Context) error {
	slog.Info("Starting bot")

	s.startHealthServer()
	s.initializeBotCommands(ctx)

	if err := s.logBotInfo(ctx); err != nil {
		return err
	}

	updates, err := s.startLongPolling(ctx)
	if err != nil {
		return err
	}

	return s.ProcessUpdates(ctx, updates)
}

// startHealthServer starts the health check server in a goroutine if enabled
func (s *BotService) startHealthServer() {
	if !s.config.Health.Enabled {
		return
	}

	addr := fmt.Sprintf("%s:%d", s.config.Health.Address, s.config.Health.Port)
	go func() {
		if err := s.health.StartServer(addr); err != nil {
			slog.Error("Health check server error", "error", err)
		}
	}()
}

// initializeBotCommands sets up bot commands, logs warning on failure
func (s *BotService) initializeBotCommands(ctx context.Context) {
	if err := s.setBotCommands(ctx); err != nil {
		slog.Warn("Failed to set bot commands", "error", err)
	}
}

// logBotInfo retrieves and logs bot information
func (s *BotService) logBotInfo(ctx context.Context) error {
	me, err := s.bot.GetMe(ctx)
	if err != nil {
		return fmt.Errorf("failed to get bot info: %w", err)
	}
	slog.Info("Bot started", "username", me.Username, "id", me.ID)
	return nil
}

// startLongPolling starts long polling and returns the updates channel
func (s *BotService) startLongPolling(ctx context.Context) (<-chan telego.Update, error) {
	updates, err := s.bot.UpdatesViaLongPolling(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to get updates channel: %w", err)
	}
	return updates, nil
}

// ProcessUpdates processes incoming updates
func (s *BotService) ProcessUpdates(ctx context.Context, updates <-chan telego.Update) error {
	for {
		select {
		case <-ctx.Done():
			slog.Info("Stopping bot")
			return nil
		case update, ok := <-updates:
			if !ok {
				return errors.New("updates channel closed")
			}
			s.handleUpdateAsync(ctx, update)
		}
	}
}

// handleUpdateAsync processes a single update asynchronously with error handling
func (s *BotService) handleUpdateAsync(ctx context.Context, update telego.Update) {
	s.health.RecordUpdate()

	go func(upd telego.Update) {
		defer middleware.RecoverPanic()

		if err := s.handler.Handle(ctx, upd); err != nil {
			slog.Error("Error handling update", "error", err)
			s.health.RecordError()
		}
	}(update)
}

// setBotCommands sets the bot's command menu
func (s *BotService) setBotCommands(ctx context.Context) error {
	commands := s.content.GetCommands()
	botCommands := convertToTelegramCommands(commands.Commands)

	if err := s.sendCommandsToTelegram(ctx, botCommands); err != nil {
		return err
	}

	slog.Info("Bot commands configured", "count", len(botCommands))
	return nil
}

// convertToTelegramCommands converts domain commands to Telegram bot commands
func convertToTelegramCommands(commands []domain.Command) []telego.BotCommand {
	botCommands := make([]telego.BotCommand, 0, len(commands))
	for _, cmd := range commands {
		botCommands = append(botCommands, telego.BotCommand{
			Command:     stripCommandPrefix(cmd.Command),
			Description: cmd.Description,
		})
	}
	return botCommands
}

// stripCommandPrefix removes the leading / from a command
func stripCommandPrefix(command string) string {
	if len(command) > 0 && command[0] == '/' {
		return command[1:]
	}
	return command
}

// sendCommandsToTelegram sends the commands to Telegram API
func (s *BotService) sendCommandsToTelegram(ctx context.Context, commands []telego.BotCommand) error {
	params := new(telego.SetMyCommandsParams{
		Commands: commands,
	})

	if err := s.bot.SetMyCommands(ctx, params); err != nil {
		return fmt.Errorf("failed to set commands: %w", err)
	}

	return nil
}

// Stop stops the bot gracefully
func (s *BotService) Stop(ctx context.Context) error {
	slog.Info("Stopping bot")

	// Shutdown health server
	if err := s.health.Shutdown(ctx); err != nil {
		slog.Error("Error shutting down health server", "error", err)
		return err
	}

	slog.Info("Bot stopped successfully")
	return nil
}
