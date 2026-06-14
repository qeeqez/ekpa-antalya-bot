package handler

import (
	"context"
	"fmt"
	"log/slog"
	"strings"

	"github.com/mymmrac/telego"
	"github.com/mymmrac/telego/telegoutil"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

// CommandHandler handles bot commands
type CommandHandler struct {
	content     *config.ContentRepository
	sender      domain.MessageSender
	botUsername string
}

// NewCommandHandler creates a new command handler
func NewCommandHandler(content *config.ContentRepository, sender domain.MessageSender, botUsername string) *CommandHandler {
	return &CommandHandler{
		content:     content,
		sender:      sender,
		botUsername: botUsername,
	}
}

// Supports checks if this update is a command
func (h *CommandHandler) Supports(update telego.Update) bool {
	return update.Message != nil &&
		update.Message.Text != "" &&
		strings.HasPrefix(update.Message.Text, "/")
}

// Priority returns the handler priority
func (h *CommandHandler) Priority() int {
	return 1
}

// Handle processes a command
func (h *CommandHandler) Handle(ctx context.Context, update telego.Update) error {
	message := update.Message
	chatID := telegoutil.ID(message.Chat.ID)
	command := h.normalizeCommand(message.Text)
	localeCode := locale.FromUpdate(update)

	slog.Info("Handling command", "command", command, "chat_id", message.Chat.ID, "user_id", message.From.ID)

	// Handle special /start command
	if command == "/start" {
		return h.handleStart(ctx, chatID, localeCode)
	}

	return h.handleRegisteredCommand(ctx, chatID, command, localeCode)
}

// normalizeCommand removes bot username from command if present
func (h *CommandHandler) normalizeCommand(command string) string {
	if !strings.Contains(command, "@") {
		return command
	}

	parts := strings.Split(command, "@")
	if len(parts) == 2 && strings.Contains(parts[1], h.botUsername) {
		return parts[0]
	}

	return command
}

// handleRegisteredCommand handles commands registered in the command registry
func (h *CommandHandler) handleRegisteredCommand(ctx context.Context, chatID telego.ChatID, command string, localeCode string) error {
	cmd, found := h.content.GetCommandsForLocale(localeCode).GetCommand(command)
	if !found {
		slog.Debug("Command not recognized", "command", command)
		return nil
	}

	screen, err := h.content.GetScreenForLocale(cmd.ScreenID, localeCode)
	if err != nil {
		slog.Error("Failed to get screen for command", "screen_id", cmd.ScreenID, "command", command, "error", err)
		return fmt.Errorf("failed to get screen: %w", err)
	}

	if _, err := h.sender.SendScreen(ctx, chatID, screen); err != nil {
		return fmt.Errorf("failed to send screen: %w", err)
	}

	return nil
}

// handleStart handles the /start command with special pinning logic
func (h *CommandHandler) handleStart(ctx context.Context, chatID telego.ChatID, localeCode string) error {
	screen, err := h.getMainMenuScreen(localeCode)
	if err != nil {
		return err
	}

	if err := h.sendAndPinMenu(ctx, chatID, screen); err != nil {
		return err
	}

	h.sendSecondaryMenu(ctx, chatID, screen)
	return nil
}

// getMainMenuScreen retrieves the main menu screen
func (h *CommandHandler) getMainMenuScreen(localeCode string) (*domain.Screen, error) {
	screen, err := h.content.GetScreenForLocale("MAIN_MENU", localeCode)
	if err != nil {
		return nil, fmt.Errorf("failed to get main menu: %w", err)
	}
	return screen, nil
}

// sendAndPinMenu sends the main menu and attempts to pin it
func (h *CommandHandler) sendAndPinMenu(ctx context.Context, chatID telego.ChatID, screen *domain.Screen) error {
	msg, err := h.sender.SendScreen(ctx, chatID, screen)
	if err != nil {
		return fmt.Errorf("failed to send main menu: %w", err)
	}

	if err := h.sender.PinMessage(ctx, chatID, msg.MessageID); err != nil {
		slog.Debug("Failed to pin message", "error", err)
	}

	return nil
}

// sendSecondaryMenu sends a second copy of the menu (not pinned)
func (h *CommandHandler) sendSecondaryMenu(ctx context.Context, chatID telego.ChatID, screen *domain.Screen) {
	if _, err := h.sender.SendScreen(ctx, chatID, screen); err != nil {
		slog.Warn("Failed to send second menu", "error", err)
	}
}
