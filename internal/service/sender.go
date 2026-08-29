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
	if s.shouldUseRichMessage(screen) {
		return s.sendRichScreen(ctx, chatID, screen)
	}

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
	if s.shouldUseRichMessage(screen) {
		return s.editRichScreen(ctx, chatID, messageID, screen)
	}

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

func (s *TelegramSender) shouldUseRichMessage(screen *domain.Screen) bool {
	return screen != nil && (screen.ParseMode == domain.ParseModeRichHTML || screen.ParseMode == domain.ParseModeRichMarkdown)
}

func (s *TelegramSender) sendRichScreen(ctx context.Context, chatID telego.ChatID, screen *domain.Screen) (*telego.Message, error) {
	params := &telego.SendRichMessageParams{
		ChatID: chatID,
	}

	if screen.ParseMode == domain.ParseModeRichMarkdown {
		params.RichMessage = telegoutil.RichMessageMarkdown(screen.Text)
	} else {
		params.RichMessage = telegoutil.RichMessageHTML(screen.Text)
	}
	params.RichMessage.IsRtl = screen.IsRTL

	if screen.DisableWebPreview {
		slog.Debug("Ignoring disable_web_preview for rich message", "screen_id", screen.ID)
	}

	if len(screen.InlineKeyboard.Rows) > 0 {
		params.ReplyMarkup = s.buildInlineKeyboard(screen.InlineKeyboard)
	}

	message, err := s.bot.SendRichMessage(ctx, params)
	if err != nil {
		return nil, fmt.Errorf("failed to send rich message: %w", err)
	}

	return message, nil
}

func (s *TelegramSender) editRichScreen(ctx context.Context, chatID telego.ChatID, messageID int, screen *domain.Screen) (*telego.Message, error) {
	params := &telego.EditMessageTextParams{
		ChatID:    chatID,
		MessageID: messageID,
	}

	if screen.ParseMode == domain.ParseModeRichMarkdown {
		params.RichMessage = new(telego.InputRichMessage).WithMarkdown(screen.Text)
	} else {
		params.RichMessage = new(telego.InputRichMessage).WithHTML(screen.Text)
	}
	params.RichMessage.IsRtl = screen.IsRTL

	if screen.DisableWebPreview {
		slog.Debug("Ignoring disable_web_preview for rich message", "screen_id", screen.ID)
	}

	if len(screen.InlineKeyboard.Rows) > 0 {
		params.ReplyMarkup = s.buildInlineKeyboard(screen.InlineKeyboard)
	}

	message, err := s.bot.EditMessageText(ctx, params)
	if err != nil {
		return nil, fmt.Errorf("failed to edit rich message: %w", err)
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
