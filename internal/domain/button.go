package domain

// ButtonType represents the type of button
type ButtonType string

const (
	ButtonTypeCallback ButtonType = "callback"
	ButtonTypeURL      ButtonType = "url"
)

// Button represents a single inline keyboard button
type Button struct {
	ID           string     `yaml:"id"`
	Text         string     `yaml:"text"`
	Type         ButtonType `yaml:"type"`
	CallbackData string     `yaml:"callback"`
	URL          string     `yaml:"url,omitempty"`
}

// ButtonRow represents a row of buttons in the inline keyboard
type ButtonRow struct {
	Buttons []Button `yaml:"buttons"`
}

// InlineKeyboard represents the full inline keyboard markup
type InlineKeyboard struct {
	Rows []ButtonRow `yaml:"rows"`
}

// Validate checks if the button configuration is valid
func (b *Button) Validate() error {
	if b.Text == "" {
		return ErrInvalidButton("button text cannot be empty")
	}

	switch b.Type {
	case ButtonTypeCallback:
		if b.CallbackData == "" {
			return ErrInvalidButton("callback button must have callback data")
		}
	case ButtonTypeURL:
		if b.URL == "" {
			return ErrInvalidButton("URL button must have URL")
		}
	default:
		return ErrInvalidButton("unknown button type: " + string(b.Type))
	}

	return nil
}
