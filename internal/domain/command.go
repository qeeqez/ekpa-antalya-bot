package domain

// Command represents a bot command
type Command struct {
	Command     string `yaml:"command"`
	Description string `yaml:"description"`
	ScreenID    string `yaml:"screen_id"`
	IsPinned    bool   `yaml:"is_pinned"`
}

// CommandRegistry holds all bot commands
type CommandRegistry struct {
	Commands []Command `yaml:"commands"`
}

// GetCommand returns a command by its name
func (r *CommandRegistry) GetCommand(command string) (*Command, bool) {
	for i := range r.Commands {
		if r.Commands[i].Command == command {
			return &r.Commands[i], true
		}
	}
	return nil, false
}
