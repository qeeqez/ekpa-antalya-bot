package service

import (
	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/handler"
	"github.com/qeeqez/ekpaantalyabot/internal/health"
)

// BotService manages the Telegram bot lifecycle
type BotService struct {
	bot     *telego.Bot
	content *config.ContentRepository
	handler *handler.Chain
	health  *health.Checker
	config  *config.Config
}
