package locale

var internalMessages = map[string]map[string]string{
	"auto_back": {
		"ru": "◀️ Назад",
		"en": "◀️ Back",
		"tr": "◀️ Geri",
		"ar": "◀️ رجوع",
		"de": "◀️ Zurück",
		"es": "◀️ Atrás",
		"fr": "◀️ Retour",
		"it": "◀️ Indietro",
		"uk": "◀️ Назад",
		"kk": "◀️ Артқа",
		"ky": "◀️ Артка",
		"pl": "◀️ Wstecz",
	},
	"auto_main_menu": {
		"ru": "🏠 В главное меню",
		"en": "🏠 Main menu",
		"tr": "🏠 Ana menü",
		"ar": "🏠 القائمة الرئيسية",
		"de": "🏠 Hauptmenü",
		"es": "🏠 Menú principal",
		"fr": "🏠 Menu principal",
		"it": "🏠 Menu principale",
		"uk": "🏠 Головне меню",
		"kk": "🏠 Басты мәзір",
		"ky": "🏠 Башкы меню",
		"pl": "🏠 Menu główne",
	},
	"fallback_error": {
		"ru": "Извините, произошла ошибка.",
		"en": "Sorry, something went wrong.",
		"tr": "Üzgünüz, bir şeyler ters gitti.",
		"ar": "عذرًا، حدث خطأ ما.",
		"de": "Entschuldigung, etwas ist schiefgelaufen.",
		"es": "Lo sentimos, algo salió mal.",
		"fr": "Désolé, une erreur s'est produite.",
		"it": "Spiacente, qualcosa è andato storto.",
		"uk": "Вибачте, сталася помилка.",
		"kk": "Кешіріңіз, қате орын алды.",
		"ky": "Кечиресиз, ката кетти.",
		"pl": "Przepraszamy, wystąpił błąd.",
	},
}

// Text returns a localized internal message with Russian fallback.
func Text(key, locale string) string {
	if byLocale, ok := internalMessages[key]; ok {
		if text, ok := byLocale[locale]; ok {
			return text
		}
		if text, ok := byLocale[DefaultLocale]; ok {
			return text
		}
	}

	return ""
}
