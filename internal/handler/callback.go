package handler

import (
	"context"
	"fmt"
	"log/slog"

	"github.com/mymmrac/telego"
	"github.com/mymmrac/telego/telegoutil"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

// CallbackHandler handles callback queries from inline buttons
type CallbackHandler struct {
	content *config.ContentRepository
	sender  domain.MessageSender
}

// NewCallbackHandler creates a new callback handler
func NewCallbackHandler(content *config.ContentRepository, sender domain.MessageSender) *CallbackHandler {
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
	slog.Info("Handling callback", "callback", callback.Data, "user_id", callback.From.ID)
	localeCode := locale.FromUpdate(update)

	message := h.validateCallbackMessage(callback)
	if message == nil {
		return nil
	}

	targetScreen, err := h.findTargetScreen(callback.Data, localeCode)
	if err != nil {
		return h.handleScreenNotFound(ctx, message, callback.Data, localeCode, err)
	}

	return h.updateMessageWithScreen(ctx, message, targetScreen)
}

// validateCallbackMessage validates that the callback has an associated message
func (h *CallbackHandler) validateCallbackMessage(callback *telego.CallbackQuery) *telego.Message {
	if callback.Message == nil {
		slog.Warn("Callback query has no message")
		return nil
	}
	return callback.Message.(*telego.Message)
}

// handleScreenNotFound handles the case when a target screen cannot be found
func (h *CallbackHandler) handleScreenNotFound(ctx context.Context, message *telego.Message, callbackData string, localeCode string, err error) error {
	slog.Error("Failed to find target screen", "callback", callbackData, "error", err)

	chatID := telegoutil.ID(message.Chat.ID)
	if _, sendErr := h.sender.SendText(ctx, chatID, locale.Text("fallback_error", localeCode)); sendErr != nil {
		slog.Error("Failed to send error message", "error", sendErr)
	}

	return nil
}

// updateMessageWithScreen updates the message with a new screen
func (h *CallbackHandler) updateMessageWithScreen(ctx context.Context, message *telego.Message, screen *domain.Screen) error {
	chatID := telegoutil.ID(message.Chat.ID)

	if _, err := h.sender.EditScreen(ctx, chatID, message.MessageID, screen); err != nil {
		slog.Error("Failed to edit message", "error", err)
		return fmt.Errorf("failed to edit message: %w", err)
	}

	return nil
}

// findTargetScreen finds the target screen for a given callback data
func (h *CallbackHandler) findTargetScreen(callbackData string, localeCode string) (*domain.Screen, error) {
	// Special case: MAIN_MENU_BUTTON always goes to MAIN_MENU
	if callbackData == "MAIN_MENU_BUTTON" {
		return h.content.GetScreenForLocale("MAIN_MENU", localeCode)
	}

	// Try to find screen through navigation rules
	if screen, err := h.findScreenByNavigation(callbackData, localeCode); err == nil {
		return screen, nil
	}

	// Try to use callback data as screen ID directly
	if screen, err := h.content.GetScreenForLocale(callbackData, localeCode); err == nil {
		return screen, nil
	}

	return nil, fmt.Errorf("no target screen found for callback: %s", callbackData)
}

// findScreenByNavigation searches for a screen by checking navigation rules
func (h *CallbackHandler) findScreenByNavigation(callbackData string, localeCode string) (*domain.Screen, error) {
	allScreens := h.content.GetAllScreens()

	for _, screen := range allScreens {
		// Check if this screen has a navigation rule for this callback
		if targetID, found := screen.GetNavigationTarget(callbackData); found {
			return h.content.GetScreenForLocale(targetID, localeCode)
		}

		// Check if the screen ID directly matches the callback
		if screen.ID == callbackData {
			return h.content.GetScreenForLocale(screen.ID, localeCode)
		}
	}

	return nil, fmt.Errorf("no screen found by navigation for: %s", callbackData)
}
