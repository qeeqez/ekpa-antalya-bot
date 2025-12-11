package config

import (
	"fmt"

	"github.com/caarlos0/env/v11"
)

// Config represents the main application configuration
type Config struct {
	Bot    BotConfig
	Health HealthConfig
	Debug  bool `env:"DEBUG" envDefault:"false"`
}

// BotConfig holds Telegram bot configuration
type BotConfig struct {
	Token    string `env:"BOT_TOKEN"`
	Username string `env:"BOT_USERNAME" envDefault:"EkpaAntalyaBot"`
}

// HealthConfig holds health check configuration
type HealthConfig struct {
	Enabled bool   `env:"HEALTH_ENABLED" envDefault:"true"`
	Port    int    `env:"HEALTH_PORT" envDefault:"8080"`
	Address string `env:"HEALTH_ADDRESS" envDefault:"0.0.0.0"`
}

// Load loads configuration from environment variables
func Load() (*Config, error) {
	var cfg Config

	// Parse environment variables
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

	// Set defaults for health check
	if c.Health.Port == 0 {
		c.Health.Port = 8080
	}
	if c.Health.Address == "" {
		c.Health.Address = "0.0.0.0"
	}

	return nil
}
