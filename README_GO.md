# Ekpa Antalya Telegram Bot (Go)

A complete rewrite of the Ekpa Antalya Telegram Bot from Java/Spring Boot to Go using the [mymmrac/telego](https://github.com/mymmrac/telego) library.

## Architecture

### Design Philosophy

This Go implementation follows a **data-driven architecture** where all content, buttons, and navigation are defined in YAML configuration files. This approach provides:

- **90% less code**: From 257 Java files to ~20 Go files + YAML configs
- **Instant updates**: Change content without recompilation
- **Easy maintenance**: Non-developers can update text and navigation
- **Type safety**: Strong typing in domain layer with runtime validation
- **Performance**: Native Go concurrency vs Java threads

### Directory Structure

```
.
├── cmd/bot/                    # Application entry point
├── internal/
│   ├── config/                 # Configuration loading
│   ├── domain/                 # Domain models (Button, Screen, etc.)
│   ├── handler/                # Update handlers (Command, Callback)
│   ├── service/                # Bot service and message sender
│   └── telegram/               # Telegram client wrapper (future)
├── configs/
│   ├── bot.yaml               # Bot configuration
│   └── content/               # Content YAML files
│       ├── main_menu.yaml
│       ├── chats.yaml
│       ├── directions.yaml
│       ├── info_ekpa.yaml
│       ├── info_useful.yaml
│       ├── transport.yaml
│       ├── medical.yaml
│       ├── delivery.yaml
│       ├── documents.yaml
│       └── other.yaml
└── go.mod
```

## Getting Started

### Prerequisites

- Go 1.21 or higher
- Telegram Bot Token (get from [@BotFather](https://t.me/BotFather))

### Installation

1. Clone the repository
2. Install dependencies:
```bash
go mod download
```

3. Update bot token in `configs/bot.yaml`:
```yaml
bot:
  username: "YourBotName"
  token: "YOUR_BOT_TOKEN"
```

### Running

```bash
# Run directly
go run cmd/bot/main.go

# Build and run
go build -o ekpabot cmd/bot/main.go
./ekpabot

# With custom config
./ekpabot -config path/to/config.yaml
```

## Configuration

### Bot Configuration (`configs/bot.yaml`)

```yaml
bot:
  username: "EkpaAntalyaBot"
  token: "YOUR_BOT_TOKEN"

content:
  directory: "configs/content"
```

### Content Files

Content is organized in YAML files under `configs/content/`. Each file defines screens with:

- **Text**: Message content (supports MarkdownV2)
- **Buttons**: Inline keyboard buttons
- **Navigation**: Callback routing to target screens

Example screen:

```yaml
screens:
  - id: "MAIN_MENU"
    text: "*Главное меню*"
    parse_mode: "MarkdownV2"
    inline_keyboard:
      rows:
        - buttons:
            - id: "our_chats"
              text: "💬 Наши Чаты"
              type: "callback"
              callback: "OUR_CHATS_BUTTON"
        - buttons:
            - id: "directions"
              text: "📍 Что рядом?"
              type: "callback"
              callback: "DIRECTIONS_BUTTON"
    navigation:
      - callback: "OUR_CHATS_BUTTON"
        target: "OUR_CHATS"
      - callback: "DIRECTIONS_BUTTON"
        target: "DIRECTIONS"
```

## Development

### Adding New Content

1. Edit existing YAML files in `configs/content/` or create new ones
2. Define screens with unique IDs
3. Add buttons and navigation rules
4. Restart the bot (no recompilation needed)

### Code Structure

- **Domain Layer** (`internal/domain/`): Pure business logic, no dependencies
- **Config Layer** (`internal/config/`): Configuration loading and content repository
- **Handler Layer** (`internal/handler/`): Update processing (commands, callbacks)
- **Service Layer** (`internal/service/`): Bot orchestration and message sending

### Handler Chain

The bot uses a chain of responsibility pattern:

1. **CommandHandler** (Priority 1): Handles `/start`, `/menu`, etc.
2. **CallbackHandler** (Priority 2): Handles button clicks

Each handler checks if it `Supports()` the update and processes it accordingly.

## Deployment

### Docker

```bash
# Build image
docker build -t ekpabot .

# Run container
docker run -d \
  -v $(pwd)/configs:/app/configs \
  --name ekpabot \
  ekpabot
```

### Docker Compose

```bash
docker-compose up -d
```

## Comparison with Java Version

| Feature | Java (Spring Boot) | Go (Telego) |
|---------|-------------------|-------------|
| **Files** | 257 Java files | ~20 Go files + YAML |
| **Binary Size** | ~50MB+ JAR | ~10-15MB |
| **Startup Time** | 3-5 seconds | <1 second |
| **Memory** | ~200MB+ | ~20-30MB |
| **Content Updates** | Recompile + redeploy | Edit YAML, restart |
| **Concurrency** | Thread pools | Goroutines |
| **Dependencies** | Spring ecosystem | Minimal (telego + yaml) |

## Migration Notes

- All callback data identifiers remain unchanged for backward compatibility
- All commands (`/start`, `/menu`, `/botlink`, `/ourchats`) work identically
- User experience is identical to Java version
- Existing user sessions continue working seamlessly

## Features

- ✅ Command handling (`/start`, `/menu`, `/botlink`, `/ourchats`)
- ✅ Callback query handling (inline buttons)
- ✅ Dynamic content loading from YAML
- ✅ Hierarchical menu navigation
- ✅ MarkdownV2 formatting support
- ✅ URL and callback buttons
- ✅ Graceful shutdown
- ✅ Error handling and logging

## Contributing

1. Update content: Edit YAML files in `configs/content/`
2. Add features: Follow the existing architecture patterns
3. Test thoroughly: Ensure all screens and navigation work correctly
4. Document changes: Update this README if adding new features

## License

[Your License Here]

## Support

For questions or issues, contact [@qeeqez](https://t.me/qeeqez) or create an issue in the repository.
