package locale

import "strings"

const (
	DefaultLocale = "ru"
)

type Profile struct {
	Code string
	Name string
}

var localeProfiles = []Profile{
	{Code: "ru", Name: "Russian"},
	{Code: "en", Name: "English"},
	{Code: "tr", Name: "Turkish"},
	{Code: "ar", Name: "Arabic"},
	{Code: "de", Name: "German"},
	{Code: "es", Name: "Spanish"},
	{Code: "fr", Name: "French"},
	{Code: "it", Name: "Italian"},
	{Code: "uk", Name: "Ukrainian"},
	{Code: "kk", Name: "Kazakh"},
	{Code: "ky", Name: "Kyrgyz"},
	{Code: "pl", Name: "Polish"},
}

var SupportedLocales = supportedLocaleCodes()

var supportedLocaleSet = func() map[string]struct{} {
	set := make(map[string]struct{}, len(localeProfiles))
	for _, profile := range localeProfiles {
		set[profile.Code] = struct{}{}
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

// SupportedReleaseLocales returns all supported locales in display order.
func SupportedReleaseLocales() []string {
	return supportedLocaleCodes()
}

// Profiles returns all supported locale profiles in display order.
func Profiles() []Profile {
	out := make([]Profile, len(localeProfiles))
	copy(out, localeProfiles)
	return out
}

func supportedLocaleCodes() []string {
	out := make([]string, 0, len(localeProfiles))
	for _, profile := range localeProfiles {
		out = append(out, profile.Code)
	}
	return out
}
