package service

import (
	"context"
	"errors"
	"fmt"
	"log/slog"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
	"github.com/qeeqez/ekpaantalyabot/internal/middleware"
)

// Start starts the bot.
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

// startHealthServer starts the health check server in a goroutine if enabled.
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

// initializeBotCommands sets up bot commands, logs warning on failure.
func (s *BotService) initializeBotCommands(ctx context.Context) {
	if err := s.setBotCommands(ctx); err != nil {
		slog.Warn("Failed to set bot commands", "error", err)
	}
}

// logBotInfo retrieves and logs bot information.
func (s *BotService) logBotInfo(ctx context.Context) error {
	me, err := s.bot.GetMe(ctx)
	if err != nil {
		return fmt.Errorf("failed to get bot info: %w", err)
	}
	slog.Info("Bot started", "username", me.Username, "id", me.ID)
	return nil
}

// startLongPolling starts long polling and returns the updates channel.
func (s *BotService) startLongPolling(ctx context.Context) (<-chan telego.Update, error) {
	updates, err := s.bot.UpdatesViaLongPolling(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to get updates channel: %w", err)
	}
	return updates, nil
}

// ProcessUpdates processes incoming updates.
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

// handleUpdateAsync processes a single update asynchronously with error handling.
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

// setBotCommands sets the bot's command menu.
func (s *BotService) setBotCommands(ctx context.Context) error {
	for _, localeCode := range locale.SupportedReleaseLocales() {
		commands := s.content.GetCommandsForLocale(localeCode)
		botCommands := convertToTelegramCommands(commands.Commands)

		if err := s.sendCommandsToTelegram(ctx, botCommands, localeCode); err != nil {
			return err
		}
	}

	slog.Info("Bot commands configured", "locales", locale.SupportedReleaseLocales())
	return nil
}

// convertToTelegramCommands converts domain commands to Telegram bot commands.
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

// stripCommandPrefix removes the leading / from a command.
func stripCommandPrefix(command string) string {
	if len(command) > 0 && command[0] == '/' {
		return command[1:]
	}
	return command
}

// sendCommandsToTelegram sends the commands to Telegram API.
func (s *BotService) sendCommandsToTelegram(ctx context.Context, commands []telego.BotCommand, localeCode string) error {
	params := new(telego.SetMyCommandsParams{
		Commands:     commands,
		LanguageCode: localeCode,
	})

	if err := s.bot.SetMyCommands(ctx, params); err != nil {
		return fmt.Errorf("failed to set commands: %w", err)
	}

	return nil
}

// Stop stops the bot gracefully.
func (s *BotService) Stop(ctx context.Context) error {
	slog.Info("Stopping bot")

	if err := s.health.Shutdown(ctx); err != nil {
		slog.Error("Error shutting down health server", "error", err)
		return err
	}

	slog.Info("Bot stopped successfully")
	return nil
}
