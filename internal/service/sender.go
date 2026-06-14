package service

import (
	"context"
	"fmt"
	"log/slog"

	"github.com/mymmrac/telego"
	"github.com/mymmrac/telego/telegoutil"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
)

// TelegramSender implements domain.MessageSender for Telegram
type TelegramSender struct {
	bot *telego.Bot
}

// NewMessageSender creates a new Telegram message sender
func NewMessageSender(bot *telego.Bot) domain.MessageSender {
	return &TelegramSender{
		bot: bot,
	}
}

// SendScreen sends a screen as a new message
func (s *TelegramSender) SendScreen(ctx context.Context, chatID telego.ChatID, screen *domain.Screen) (*telego.Message, error) {
	msg := telegoutil.Message(chatID, screen.Text)
	s.applyScreenSettingsToSendMessage(msg, screen)

	message, err := s.bot.SendMessage(ctx, msg)
	if err != nil {
		return nil, fmt.Errorf("failed to send message: %w", err)
	}

	return message, nil
}

// EditScreen edits an existing message with a screen
func (s *TelegramSender) EditScreen(ctx context.Context, chatID telego.ChatID, messageID int, screen *domain.Screen) (*telego.Message, error) {
	msg := new(telego.EditMessageTextParams{
		ChatID:    chatID,
		MessageID: messageID,
		Text:      screen.Text,
	})
	s.applyScreenSettingsToEditMessage(msg, screen)

	message, err := s.bot.EditMessageText(ctx, msg)
	if err != nil {
		return nil, fmt.Errorf("failed to edit message: %w", err)
	}

	return message, nil
}

// applyScreenSettingsToSendMessage applies screen settings to SendMessageParams
func (s *TelegramSender) applyScreenSettingsToSendMessage(msg *telego.SendMessageParams, screen *domain.Screen) {
	if screen.ParseMode != "" {
		msg.ParseMode = string(screen.ParseMode)
	}

	if screen.DisableWebPreview {
		msg.LinkPreviewOptions = new(telego.LinkPreviewOptions{
			IsDisabled: true,
		})
	}

	if len(screen.InlineKeyboard.Rows) > 0 {
		msg.ReplyMarkup = s.buildInlineKeyboard(screen.InlineKeyboard)
	}
}

// applyScreenSettingsToEditMessage applies screen settings to EditMessageTextParams
func (s *TelegramSender) applyScreenSettingsToEditMessage(msg *telego.EditMessageTextParams, screen *domain.Screen) {
	if screen.ParseMode != "" {
		msg.ParseMode = string(screen.ParseMode)
	}

	if screen.DisableWebPreview {
		msg.LinkPreviewOptions = new(telego.LinkPreviewOptions{
			IsDisabled: true,
		})
	}

	if len(screen.InlineKeyboard.Rows) > 0 {
		msg.ReplyMarkup = s.buildInlineKeyboard(screen.InlineKeyboard)
	}
}

// SendText sends a simple text message
func (s *TelegramSender) SendText(ctx context.Context, chatID telego.ChatID, text string) (*telego.Message, error) {
	msg := telegoutil.Message(chatID, text)

	message, err := s.bot.SendMessage(ctx, msg)
	if err != nil {
		return nil, fmt.Errorf("failed to send text: %w", err)
	}

	return message, nil
}

// PinMessage pins a message in a chat
func (s *TelegramSender) PinMessage(ctx context.Context, chatID telego.ChatID, messageID int) error {
	params := new(telego.PinChatMessageParams{
		ChatID:    chatID,
		MessageID: messageID,
	})

	if err := s.bot.PinChatMessage(ctx, params); err != nil {
		slog.Debug("Failed to pin message", "error", err)
		// Don't return error as pinning might fail due to permissions
	}

	return nil
}
