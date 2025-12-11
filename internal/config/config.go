package config

import (
	"fmt"
	"os"

	"github.com/caarlos0/env/v11"
	"gopkg.in/yaml.v3"
)

// Config represents the main application configuration
type Config struct {
	Bot     BotConfig     `yaml:"bot"`
	Content ContentConfig `yaml:"content"`
	Health  HealthConfig  `yaml:"health"`
}

// BotConfig holds Telegram bot configuration
type BotConfig struct {
	Token    string `yaml:"token" env:"BOT_TOKEN"`
	Username string `yaml:"username" env:"BOT_USERNAME" envDefault:"EkpaAntalyaBot"`
}

// ContentConfig holds content-related configuration
type ContentConfig struct {
	Directory string `yaml:"directory" env:"CONTENT_DIR" envDefault:"configs/content"`
}

// HealthConfig holds health check configuration
type HealthConfig struct {
	Enabled bool   `yaml:"enabled" env:"HEALTH_ENABLED" envDefault:"true"`
	Port    int    `yaml:"port" env:"HEALTH_PORT" envDefault:"8080"`
	Address string `yaml:"address" env:"HEALTH_ADDRESS" envDefault:"0.0.0.0"`
}

// Load loads configuration from a YAML file and environment variables
func Load(path string) (*Config, error) {
	var cfg Config

	// Try to load from YAML file if it exists
	if path != "" {
		data, err := os.ReadFile(path)
		if err != nil && !os.IsNotExist(err) {
			return nil, fmt.Errorf("failed to read config file: %w", err)
		}

		if err == nil {
			if err := yaml.Unmarshal(data, &cfg); err != nil {
				return nil, fmt.Errorf("failed to parse config file: %w", err)
			}
		}
	}

	// Override with environment variables (env vars take precedence)
	if err := env.Parse(&cfg); err != nil {
		return nil, fmt.Errorf("failed to parse environment variables: %w", err)
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

	// Set defaults for health check
	if c.Health.Port == 0 {
		c.Health.Port = 8080
	}
	if c.Health.Address == "" {
		c.Health.Address = "0.0.0.0"
	}

	return nil
}
