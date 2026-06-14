package locale

import "strings"

const (
	DefaultLocale = "ru"
)

type Tier string

const (
	TierPrimary   Tier = "primary"
	TierSecondary Tier = "secondary"
)

type Profile struct {
	Code string
	Name string
	Tier Tier
}

var localeProfiles = []Profile{
	{Code: "ru", Name: "Russian", Tier: TierPrimary},
	{Code: "en", Name: "English", Tier: TierPrimary},
	{Code: "tr", Name: "Turkish", Tier: TierPrimary},
	{Code: "ar", Name: "Arabic", Tier: TierPrimary},
	{Code: "de", Name: "German", Tier: TierSecondary},
	{Code: "es", Name: "Spanish", Tier: TierSecondary},
	{Code: "fr", Name: "French", Tier: TierSecondary},
	{Code: "it", Name: "Italian", Tier: TierSecondary},
	{Code: "uk", Name: "Ukrainian", Tier: TierSecondary},
	{Code: "kk", Name: "Kazakh", Tier: TierSecondary},
	{Code: "ky", Name: "Kyrgyz", Tier: TierSecondary},
	{Code: "pl", Name: "Polish", Tier: TierSecondary},
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

// SupportedReleaseLocales are the locales we actively localize in the first rollout.
func SupportedReleaseLocales() []string {
	return localeCodesByTier(TierPrimary)
}

// SupportedSecondaryLocales are the locales that ship after the primary rollout.
func SupportedSecondaryLocales() []string {
	return localeCodesByTier(TierSecondary)
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

func localeCodesByTier(tier Tier) []string {
	out := make([]string, 0)
	for _, profile := range localeProfiles {
		if profile.Tier == tier {
			out = append(out, profile.Code)
		}
	}
	return out
}
