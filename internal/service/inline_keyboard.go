package service

import (
	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
)

// buildInlineKeyboard converts domain InlineKeyboard to Telegram InlineKeyboardMarkup.
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

	return new(telego.InlineKeyboardMarkup{
		InlineKeyboard: rows,
	})
}
