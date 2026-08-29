package locale_test

import (
	"slices"
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

func TestSupportedLocales(t *testing.T) {
	want := []string{"ru", "en", "tr", "ar", "de", "es", "fr", "it", "uk", "kk", "ky", "pl"}

	if got := locale.SupportedLocales; !slices.Equal(got, want) {
		t.Fatalf("SupportedLocales() = %v, want %v", got, want)
	}

	if got := locale.SupportedReleaseLocales(); !slices.Equal(got, want) {
		t.Fatalf("SupportedReleaseLocales() = %v, want %v", got, want)
	}
}

func TestNormalize(t *testing.T) {
	tests := []struct {
		name string
		code string
		want string
	}{
		{name: "empty", code: "", want: locale.DefaultLocale},
		{name: "base language", code: "en", want: "en"},
		{name: "regional variant", code: "en-GB", want: "en"},
		{name: "arabic variant", code: "ar-EG", want: "ar"},
		{name: "script and region variant", code: "de-DE", want: "de"},
		{name: "unsupported language", code: "ja", want: locale.DefaultLocale},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := locale.Normalize(tt.code); got != tt.want {
				t.Fatalf("Normalize(%q) = %q, want %q", tt.code, got, tt.want)
			}
		})
	}
}

func TestTextFallsBackToRussian(t *testing.T) {
	if got := locale.Text("fallback_error", "ja"); got != "Извините, произошла ошибка." {
		t.Fatalf("unexpected fallback text: %q", got)
	}
}

func TestIsRTL(t *testing.T) {
	tests := []struct {
		name string
		code string
		want bool
	}{
		{name: "arabic", code: "ar", want: true},
		{name: "arabic variant", code: "ar-EG", want: true},
		{name: "english", code: "en", want: false},
		{name: "russian", code: "ru", want: false},
		{name: "unknown", code: "ja", want: false},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := locale.IsRTL(tt.code); got != tt.want {
				t.Fatalf("IsRTL(%q) = %v, want %v", tt.code, got, tt.want)
			}
		})
	}
}
