package format

import (
	"fmt"
	"regexp"
	"strings"
)

var markdownV2RenderableStripPatterns = []*regexp.Regexp{
	regexp.MustCompile("(?s)```.*?```"),
	regexp.MustCompile("`[^`]*`"),
	regexp.MustCompile(`\[[^\]]*\]\([^)]+\)`),
}

var markdownV2ReservedPlain = map[byte]struct{}{
	'.': {},
	'!': {},
	'(': {},
	')': {},
	'-': {},
}

// EscapeMarkdownV2 escapes reserved MarkdownV2 characters outside of code blocks, inline code and links.
func EscapeMarkdownV2(text string) string {
	if text == "" {
		return text
	}

	placeholders := make([]string, 0)
	sanitized := text
	for _, pattern := range markdownV2RenderableStripPatterns {
		sanitized = pattern.ReplaceAllStringFunc(sanitized, func(match string) string {
			placeholders = append(placeholders, match)
			return fmt.Sprintf("@@MDV2%d@@", len(placeholders)-1)
		})
	}

	escaped := escapeMarkdownV2PlainText(sanitized)
	for i, placeholder := range placeholders {
		escaped = strings.ReplaceAll(escaped, fmt.Sprintf("@@MDV2%d@@", i), placeholder)
	}

	return escaped
}

// ValidateMarkdownV2Text verifies that plain rendered text does not contain raw reserved characters.
func ValidateMarkdownV2Text(text string) error {
	if text == "" {
		return nil
	}

	sanitized := text
	for _, pattern := range markdownV2RenderableStripPatterns {
		sanitized = pattern.ReplaceAllString(sanitized, "")
	}

	for i := range len(sanitized) {
		if _, ok := markdownV2ReservedPlain[sanitized[i]]; !ok {
			continue
		}
		if i > 0 && sanitized[i-1] == '\\' {
			continue
		}
		return fmt.Errorf("unescaped reserved character %q", sanitized[i])
	}

	return nil
}

func escapeMarkdownV2PlainText(text string) string {
	escaped := make([]byte, 0, len(text))
	for i := range len(text) {
		if _, ok := markdownV2ReservedPlain[text[i]]; ok && (i == 0 || text[i-1] != '\\') {
			escaped = append(escaped, '\\')
		}
		escaped = append(escaped, text[i])
	}
	return string(escaped)
}
