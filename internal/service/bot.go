package service

import (
	"context"
	"fmt"
	"log"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/handler"
	"github.com/qeeqez/ekpaantalyabot/internal/health"
	"github.com/qeeqez/ekpaantalyabot/internal/middleware"
)

// BotService manages the Telegram bot lifecycle
type BotService struct {
	bot     *telego.Bot
	content *config.ContentRepository
	handler *handler.Chain
	health  *health.Checker
	config  *config.Config
}

// NewBotService creates a new bot service
func NewBotService(cfg *config.Config) (*BotService, error) {
	// Create bot instance with optional debug logging
	var bot *telego.Bot
	var err error

	if cfg.Debug {
		log.Println("Debug mode enabled - verbose logging active")
		bot, err = telego.NewBot(cfg.Bot.Token, telego.WithDefaultDebugLogger())
	} else {
		bot, err = telego.NewBot(cfg.Bot.Token)
	}

	if err != nil {
		return nil, fmt.Errorf("failed to create bot: %w", err)
	}

	// Create health checker first (so we can track content loading)
	healthChecker := health.NewChecker()

	// Load content (navigation hierarchy is built automatically)
	content, err := config.NewContentRepository(cfg.Content.Directory)
	if err != nil {
		return nil, fmt.Errorf("failed to load content: %w", err)
	}
	healthChecker.RecordContentLoad()

	// Create message sender
	sender := NewMessageSender(bot)

	// Create handlers
	commandHandler := handler.NewCommandHandler(content, sender, cfg.Bot.Username)
	callbackHandler := handler.NewCallbackHandler(content, sender)

	// Create handler chain
	handlerChain := handler.NewChain(commandHandler, callbackHandler)

	service := &BotService{
		bot:     bot,
		content: content,
		handler: handlerChain,
		health:  healthChecker,
		config:  cfg,
	}

	return service, nil
}

// Start starts the bot
func (s *BotService) Start(ctx context.Context) error {
	log.Println("Starting bot...")

	// Start health check server if enabled
	if s.config.Health.Enabled {
		addr := fmt.Sprintf("%s:%d", s.config.Health.Address, s.config.Health.Port)
		go func() {
			if err := s.health.StartServer(addr); err != nil {
				log.Printf("Health check server error: %v", err)
			}
		}()
	}

	// Set bot commands
	if err := s.setBotCommands(); err != nil {
		log.Printf("Failed to set bot commands: %v", err)
	}

	// Get bot info
	me, err := s.bot.GetMe(ctx)
	if err != nil {
		return fmt.Errorf("failed to get bot info: %w", err)
	}
	log.Printf("Bot started: @%s (ID: %d)", me.Username, me.ID)

	// Get updates channel
	updates, err := s.bot.UpdatesViaLongPolling(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to get updates channel: %w", err)
	}

	// Process updates
	s.processUpdates(ctx, updates)

	return nil
}

// processUpdates processes incoming updates
func (s *BotService) processUpdates(ctx context.Context, updates <-chan telego.Update) {
	for {
		select {
		case <-ctx.Done():
			log.Println("Stopping bot...")
			return
		case update := <-updates:
			// Record update for health check
			s.health.RecordUpdate()

			// Process update asynchronously with panic recovery
			go func(upd telego.Update) {
				defer middleware.RecoverPanic()

				if err := s.handler.Handle(ctx, upd); err != nil {
					log.Printf("Error handling update: %v", err)
					s.health.RecordError()
				}
			}(update)
		}
	}
}

// setBotCommands sets the bot's command menu
func (s *BotService) setBotCommands() error {
	commands := s.content.GetCommands()

	botCommands := make([]telego.BotCommand, 0, len(commands.Commands))
	for _, cmd := range commands.Commands {
		botCommands = append(botCommands, telego.BotCommand{
			Command:     cmd.Command[1:], // Remove leading /
			Description: cmd.Description,
		})
	}

	params := &telego.SetMyCommandsParams{
		Commands: botCommands,
	}

	if err := s.bot.SetMyCommands(context.Background(), params); err != nil {
		return fmt.Errorf("failed to set commands: %w", err)
	}

	log.Printf("Set %d bot commands", len(botCommands))
	return nil
}

// Stop stops the bot gracefully
func (s *BotService) Stop(ctx context.Context) error {
	log.Println("Stopping bot...")

	// Shutdown health server
	if err := s.health.Shutdown(ctx); err != nil {
		log.Printf("Error shutting down health server: %v", err)
		return err
	}

	log.Println("Bot stopped")
	return nil
}
