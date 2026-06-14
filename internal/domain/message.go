package domain

import (
	"context"

	"github.com/mymmrac/telego"
)

// MessageSender is the interface for sending messages to users
type MessageSender interface {
	SendScreen(ctx context.Context, chatID telego.ChatID, screen *Screen) (*telego.Message, error)
	EditScreen(ctx context.Context, chatID telego.ChatID, messageID int, screen *Screen) (*telego.Message, error)
	SendText(ctx context.Context, chatID telego.ChatID, text string) (*telego.Message, error)
	PinMessage(ctx context.Context, chatID telego.ChatID, messageID int) error
}

// ParseMode represents Telegram message parse mode
type ParseMode string

const (
	ParseModeMarkdownV2 ParseMode = "MarkdownV2"
	ParseModeHTML       ParseMode = "HTML"
	ParseModeMarkdown   ParseMode = "Markdown"
	ParseModeNone       ParseMode = ""
)

// Screen represents a single screen/menu in the bot
type Screen struct {
	ID                string                  `yaml:"id"`
	Text              string                  `yaml:"text"`
	ParseMode         ParseMode               `yaml:"parse_mode"`
	DisableWebPreview bool                    `yaml:"disable_web_preview"`
	InlineKeyboard    InlineKeyboard          `yaml:"inline_keyboard"`
	NavigationTargets []Navigation            `yaml:"navigation,omitempty"`
	Locales           map[string]ScreenLocale `yaml:"locales,omitempty"`
}

// ScreenLocale stores localized screen text and button labels.
type ScreenLocale struct {
	Text        string            `yaml:"text,omitempty"`
	ButtonTexts map[string]string `yaml:"button_texts,omitempty"`
}

// Navigation defines how callbacks map to target screens
type Navigation struct {
	Callback string `yaml:"callback"`
	Target   string `yaml:"target"`
}

// Validate checks if the screen configuration is valid
func (s *Screen) Validate() error {
	if s.ID == "" {
		return ErrInvalidScreen("screen ID cannot be empty")
	}
	if s.Text == "" {
		return ErrInvalidScreen("screen text cannot be empty")
	}

	// Validate all buttons
	for rowIdx, row := range s.InlineKeyboard.Rows {
		for btnIdx, btn := range row.Buttons {
			if err := btn.Validate(); err != nil {
				return ErrInvalidScreen("invalid button at row %d, position %d: %v", rowIdx, btnIdx, err)
			}
		}
	}

	return nil
}

// GetNavigationTarget returns the target screen ID for a given callback
func (s *Screen) GetNavigationTarget(callback string) (string, bool) {
	for _, nav := range s.NavigationTargets {
		if nav.Callback == callback {
			return nav.Target, true
		}
	}
	return "", false
}
