package service

import (
	"context"
	"fmt"
	"log"

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

	if screen.ParseMode != "" {
		msg.ParseMode = string(screen.ParseMode)
	}

	if screen.DisableWebPreview {
		msg.LinkPreviewOptions = &telego.LinkPreviewOptions{
			IsDisabled: true,
		}
	}

	if len(screen.InlineKeyboard.Rows) > 0 {
		msg.ReplyMarkup = s.buildInlineKeyboard(screen.InlineKeyboard)
	}

	message, err := s.bot.SendMessage(ctx, msg)
	if err != nil {
		return nil, fmt.Errorf("failed to send message: %w", err)
	}

	return message, nil
}

// EditScreen edits an existing message with a screen
func (s *TelegramSender) EditScreen(ctx context.Context, chatID telego.ChatID, messageID int, screen *domain.Screen) (*telego.Message, error) {
	msg := &telego.EditMessageTextParams{
		ChatID:    chatID,
		MessageID: messageID,
		Text:      screen.Text,
	}

	if screen.ParseMode != "" {
		msg.ParseMode = string(screen.ParseMode)
	}

	if screen.DisableWebPreview {
		msg.LinkPreviewOptions = &telego.LinkPreviewOptions{
			IsDisabled: true,
		}
	}

	if len(screen.InlineKeyboard.Rows) > 0 {
		msg.ReplyMarkup = s.buildInlineKeyboard(screen.InlineKeyboard)
	}

	message, err := s.bot.EditMessageText(ctx, msg)
	if err != nil {
		return nil, fmt.Errorf("failed to edit message: %w", err)
	}

	return message, nil
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
	params := &telego.PinChatMessageParams{
		ChatID:    chatID,
		MessageID: messageID,
	}

	if err := s.bot.PinChatMessage(ctx, params); err != nil {
		log.Printf("Failed to pin message: %v", err)
		// Don't return error as pinning might fail due to permissions
	}

	return nil
}

// buildInlineKeyboard converts domain InlineKeyboard to Telegram InlineKeyboardMarkup
func (s *TelegramSender) buildInlineKeyboard(keyboard domain.InlineKeyboard) *telego.InlineKeyboardMarkup {
	rows := make([][]telego.InlineKeyboardButton, 0, len(keyboard.Rows))

	for _, row := range keyboard.Rows {
		buttons := make([]telego.InlineKeyboardButton, 0, len(row.Buttons))

		for _, btn := range row.Buttons {
			button := telego.InlineKeyboardButton{
				Text: btn.Text,
			}

			switch btn.Type {
			case domain.ButtonTypeCallback:
				button.CallbackData = btn.CallbackData
			case domain.ButtonTypeURL:
				button.URL = btn.URL
			}

			buttons = append(buttons, button)
		}

		rows = append(rows, buttons)
	}

	return &telego.InlineKeyboardMarkup{
		InlineKeyboard: rows,
	}
}
