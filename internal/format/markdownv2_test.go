package format_test

import (
	"testing"

	"github.com/qeeqez/ekpaantalyabot/internal/format"
)

func TestEscapeMarkdownV2(t *testing.T) {
	got := format.EscapeMarkdownV2("Hello. (world) - ok!")
	want := "Hello\\. \\(world\\) \\- ok\\!"
	if got != want {
		t.Fatalf("expected %q, got %q", want, got)
	}
}

func TestEscapeMarkdownV2PreservesCodeAndLinks(t *testing.T) {
	got := format.EscapeMarkdownV2("Use `foo.bar` and [text](https://example.com/a-b).")
	want := "Use `foo.bar` and [text](https://example.com/a-b)\\."
	if got != want {
		t.Fatalf("expected %q, got %q", want, got)
	}
}

func TestValidateMarkdownV2Text(t *testing.T) {
	if err := format.ValidateMarkdownV2Text("Hello\\. world\\!"); err != nil {
		t.Fatalf("expected valid markdown, got %v", err)
	}

	if err := format.ValidateMarkdownV2Text("Hello. world"); err == nil {
		t.Fatal("expected validation error, got nil")
	}
}
