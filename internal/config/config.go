package config

import (
	"fmt"
	"os"

	"gopkg.in/yaml.v3"
)

// Config represents the main application configuration
type Config struct {
	Bot     BotConfig     `yaml:"bot"`
	Content ContentConfig `yaml:"content"`
}

// BotConfig holds Telegram bot configuration
type BotConfig struct {
	Token    string `yaml:"token"`
	Username string `yaml:"username"`
}

// ContentConfig holds content-related configuration
type ContentConfig struct {
	Directory string `yaml:"directory"`
}

// Load loads configuration from a YAML file
func Load(path string) (*Config, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil, fmt.Errorf("failed to read config file: %w", err)
	}

	var cfg Config
	if err := yaml.Unmarshal(data, &cfg); err != nil {
		return nil, fmt.Errorf("failed to parse config file: %w", err)
	}

	if err := cfg.Validate(); err != nil {
		return nil, fmt.Errorf("invalid configuration: %w", err)
	}

	return &cfg, nil
}

// Validate checks if the configuration is valid
func (c *Config) Validate() error {
	if c.Bot.Token == "" {
		return fmt.Errorf("bot token cannot be empty")
	}
	if c.Bot.Username == "" {
		return fmt.Errorf("bot username cannot be empty")
	}
	if c.Content.Directory == "" {
		return fmt.Errorf("content directory cannot be empty")
	}
	return nil
}
