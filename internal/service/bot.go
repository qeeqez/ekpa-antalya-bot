package service

import (
	"context"
	"fmt"
	"log"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/handler"
)

// BotService manages the Telegram bot lifecycle
type BotService struct {
	bot     *telego.Bot
	content *config.ContentRepository
	handler *handler.Chain
	sender  *MessageSender
}

// NewBotService creates a new bot service
func NewBotService(cfg *config.Config) (*BotService, error) {
	// Create bot instance
	bot, err := telego.NewBot(cfg.Bot.Token, telego.WithDefaultDebugLogger())
	if err != nil {
		return nil, fmt.Errorf("failed to create bot: %w", err)
	}

	// Load content
	content, err := config.NewContentRepository(cfg.Content.Directory)
	if err != nil {
		return nil, fmt.Errorf("failed to load content: %w", err)
	}

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
		sender:  sender,
	}

	return service, nil
}

// Start starts the bot
func (s *BotService) Start(ctx context.Context) error {
	log.Println("Starting bot...")

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
			// Process update asynchronously
			go func(upd telego.Update) {
				if err := s.handler.Handle(ctx, upd); err != nil {
					log.Printf("Error handling update: %v", err)
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
func (s *BotService) Stop() {
	log.Println("Bot stopped")
}
