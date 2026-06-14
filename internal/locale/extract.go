package locale

import "github.com/mymmrac/telego"

// FromUpdate extracts the best available locale from a Telegram update.
func FromUpdate(update telego.Update) string {
	if update.Message != nil && update.Message.From != nil {
		return Normalize(update.Message.From.LanguageCode)
	}

	if update.CallbackQuery != nil {
		return Normalize(update.CallbackQuery.From.LanguageCode)
	}

	return DefaultLocale
}
