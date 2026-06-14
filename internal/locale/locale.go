package locale

import "strings"

const (
	DefaultLocale = "ru"
)

var SupportedLocales = []string{
	"ru",
	"en",
	"tr",
	"ar",
	"de",
	"es",
	"fr",
	"it",
	"uk",
	"kk",
	"ky",
	"pl",
}

var supportedLocaleSet = func() map[string]struct{} {
	set := make(map[string]struct{}, len(SupportedLocales))
	for _, code := range SupportedLocales {
		set[code] = struct{}{}
	}
	return set
}()

// Normalize converts a Telegram language tag into a supported locale code.
func Normalize(code string) string {
	if code == "" {
		return DefaultLocale
	}

	base := strings.ToLower(strings.SplitN(code, "-", 2)[0])
	if _, ok := supportedLocaleSet[base]; ok {
		return base
	}

	return DefaultLocale
}

// SupportedReleaseLocales are the locales we actively localize in the first rollout.
func SupportedReleaseLocales() []string {
	return []string{"ru", "en", "tr", "ar"}
}
