package handler

import (
	"context"
	"fmt"
	"log"
	"strings"

	"github.com/mymmrac/telego"
	"github.com/mymmrac/telego/telegoutil"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
)

// MessageSender interface for sending messages (to avoid import cycle)
type MessageSenderCommand interface {
	SendScreen(ctx context.Context, chatID telego.ChatID, screen *domain.Screen) (*telego.Message, error)
	PinMessage(ctx context.Context, chatID telego.ChatID, messageID int) error
}

// CommandHandler handles bot commands
type CommandHandler struct {
	content     *config.ContentRepository
	sender      MessageSenderCommand
	botUsername string
}

// NewCommandHandler creates a new command handler
func NewCommandHandler(content *config.ContentRepository, sender MessageSenderCommand, botUsername string) *CommandHandler {
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

	log.Printf("Handling command: %s from chat: %d", command, message.Chat.ID)

	// Handle special /start command
	if command == "/start" {
		return h.handleStart(ctx, chatID)
	}

	// Look up command in registry
	cmd, found := h.content.GetCommands().GetCommand(command)
	if !found {
		log.Printf("Command not recognized: %s", command)
		return nil
	}

	// Get the screen for this command
	screen, err := h.content.GetScreen(cmd.ScreenID)
	if err != nil {
		log.Printf("Failed to get screen %s for command %s: %v", cmd.ScreenID, command, err)
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
		log.Printf("Failed to pin message: %v", err)
	}

	// Send another menu message (not pinned)
	if _, err := h.sender.SendScreen(ctx, chatID, screen); err != nil {
		log.Printf("Failed to send second menu: %v", err)
	}

	return nil
}
