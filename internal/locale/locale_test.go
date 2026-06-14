package locale_test

import (
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/locale"
)

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
