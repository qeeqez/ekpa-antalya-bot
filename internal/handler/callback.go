package handler

import (
	"context"
	"fmt"
	"log"

	"github.com/mymmrac/telego"
	"github.com/mymmrac/telego/telegoutil"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
)

// MessageSender interface for sending messages (to avoid import cycle)
type MessageSender interface {
	SendScreen(ctx context.Context, chatID telego.ChatID, screen *domain.Screen) (*telego.Message, error)
	EditScreen(ctx context.Context, chatID telego.ChatID, messageID int, screen *domain.Screen) (*telego.Message, error)
	SendText(ctx context.Context, chatID telego.ChatID, text string) (*telego.Message, error)
}

// CallbackHandler handles callback queries from inline buttons
type CallbackHandler struct {
	content *config.ContentRepository
	sender  MessageSender
}

// NewCallbackHandler creates a new callback handler
func NewCallbackHandler(content *config.ContentRepository, sender MessageSender) *CallbackHandler {
	return &CallbackHandler{
		content: content,
		sender:  sender,
	}
}

// Supports checks if this update is a callback query
func (h *CallbackHandler) Supports(update telego.Update) bool {
	return update.CallbackQuery != nil
}

// Priority returns the handler priority
func (h *CallbackHandler) Priority() int {
	return 2
}

// Handle processes a callback query
func (h *CallbackHandler) Handle(ctx context.Context, update telego.Update) error {
	callback := update.CallbackQuery
	callbackData := callback.Data

	log.Printf("Handling callback: %s from user: %d", callbackData, callback.From.ID)

	// Validate message exists
	if callback.Message == nil {
		log.Printf("Callback query has no message")
		return nil
	}

	message := callback.Message.(*telego.Message)
	chatID := telegoutil.ID(message.Chat.ID)
	messageID := message.MessageID

	// Find the target screen based on callback data
	targetScreen, err := h.findTargetScreen(callbackData)
	if err != nil {
		log.Printf("Failed to find target screen for callback %s: %v", callbackData, err)
		// Send error message to user
		_, sendErr := h.sender.SendText(ctx, chatID, "Sorry, unhandled message was sent.")
		if sendErr != nil {
			log.Printf("Failed to send error message: %v", sendErr)
		}
		return nil
	}

	// Edit the message with the new screen
	if _, err := h.sender.EditScreen(ctx, chatID, messageID, targetScreen); err != nil {
		log.Printf("Failed to edit message: %v", err)
		return fmt.Errorf("failed to edit message: %w", err)
	}

	return nil
}

// findTargetScreen finds the target screen for a given callback data
func (h *CallbackHandler) findTargetScreen(callbackData string) (*domain.Screen, error) {
	// Search through all screens to find one with matching callback
	allScreens := h.content.GetAllScreens()

	for _, screen := range allScreens {
		// Check if this screen has a navigation rule for this callback
		if targetID, found := screen.GetNavigationTarget(callbackData); found {
			return h.content.GetScreen(targetID)
		}

		// Also check if the screen ID directly matches the callback
		// This handles cases where callback data is the screen ID itself
		if screen.ID == callbackData {
			return screen, nil
		}
	}

	// If no navigation found, try to use callback data as screen ID directly
	// This is for backwards compatibility with the Java version
	if screen, err := h.content.GetScreen(callbackData); err == nil {
		return screen, nil
	}

	return nil, fmt.Errorf("no target screen found for callback: %s", callbackData)
}
