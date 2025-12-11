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
	command := message.Text

	// Remove bot username from command if present
	if strings.Contains(command, "@") {
		parts := strings.Split(command, "@")
		if len(parts) == 2 && strings.Contains(parts[1], h.botUsername) {
			command = parts[0]
		}
	}

	slog.Info("Handling command", "command", command, "chat_id", message.Chat.ID, "user_id", message.From.ID)

	// Handle special /start command
	if command == "/start" {
		return h.handleStart(ctx, chatID)
	}

	// Look up command in registry
	cmd, found := h.content.GetCommands().GetCommand(command)
	if !found {
		slog.Debug("Command not recognized", "command", command)
		return nil
	}

	// Get the screen for this command
	screen, err := h.content.GetScreen(cmd.ScreenID)
	if err != nil {
		slog.Error("Failed to get screen for command", "screen_id", cmd.ScreenID, "command", command, "error", err)
		return fmt.Errorf("failed to get screen: %w", err)
	}

	// Send the screen
	if _, err := h.sender.SendScreen(ctx, chatID, screen); err != nil {
		return fmt.Errorf("failed to send screen: %w", err)
	}

	return nil
}

// handleStart handles the /start command with special pinning logic
func (h *CommandHandler) handleStart(ctx context.Context, chatID telego.ChatID) error {
	// Get main menu screen
	screen, err := h.content.GetScreen("MAIN_MENU")
	if err != nil {
		return fmt.Errorf("failed to get main menu: %w", err)
	}

	// Send and pin the menu message
	msg, err := h.sender.SendScreen(ctx, chatID, screen)
	if err != nil {
		return fmt.Errorf("failed to send main menu: %w", err)
	}

	// Try to pin the message
	if err := h.sender.PinMessage(ctx, chatID, msg.MessageID); err != nil {
		slog.Debug("Failed to pin message", "error", err)
	}

	// Send another menu message (not pinned)
	if _, err := h.sender.SendScreen(ctx, chatID, screen); err != nil {
		slog.Warn("Failed to send second menu", "error", err)
	}

	return nil
}
