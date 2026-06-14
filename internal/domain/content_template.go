package domain

// ScreenTemplate represents the shared, locale-neutral definition of a screen.
type ScreenTemplate struct {
	ID                string         `yaml:"id"`
	Text              string         `yaml:"text"`
	ParseMode         ParseMode      `yaml:"parse_mode"`
	DisableWebPreview bool           `yaml:"disable_web_preview"`
	InlineKeyboard    InlineKeyboard `yaml:"inline_keyboard"`
	NavigationTargets []Navigation   `yaml:"navigation,omitempty"`
}

// ToScreen converts a template into a runtime screen object.
func (s *ScreenTemplate) ToScreen() *Screen {
	if s == nil {
		return nil
	}

	screen := &Screen{
		ID:                s.ID,
		Text:              s.Text,
		ParseMode:         s.ParseMode,
		DisableWebPreview: s.DisableWebPreview,
		NavigationTargets: append([]Navigation(nil), s.NavigationTargets...),
	}
	screen.InlineKeyboard.Rows = make([]ButtonRow, len(s.InlineKeyboard.Rows))
	for i := range s.InlineKeyboard.Rows {
		screen.InlineKeyboard.Rows[i].Buttons = make([]Button, len(s.InlineKeyboard.Rows[i].Buttons))
		copy(screen.InlineKeyboard.Rows[i].Buttons, s.InlineKeyboard.Rows[i].Buttons)
	}

	return screen
}

// CommandTemplate represents the shared, locale-neutral definition of a command.
type CommandTemplate struct {
	Command  string `yaml:"command"`
	ScreenID string `yaml:"screen_id"`
	IsPinned bool   `yaml:"is_pinned"`
}

// ToCommand converts a template into a runtime command object.
func (c *CommandTemplate) ToCommand() *Command {
	if c == nil {
		return nil
	}

	return &Command{
		Command:  c.Command,
		ScreenID: c.ScreenID,
		IsPinned: c.IsPinned,
	}
}
