# EKPA 1207 Telegram Bot

[![Go Version](https://img.shields.io/badge/Go-1.26+-00ADD8?style=flat&logo=go)](https://golang.org)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

**🤖 Bot:** [@EkpaAntalyaBot](https://t.me/EkpaAntalyaBot)

Telegram bot for EKPA 1207 residents in Antalya. Quick access to complex info, documents, healthcare, transport, and essential services.

## What's Inside

- 🏢 EKPA complex management info and contacts
- 📝 VNZ (residence permit) and document guides
- 🏥 Healthcare services and emergency numbers
- 🚌 Transportation and Antalya Card info
- 📞 Embassies and important phone numbers
- 🚚 Food and goods delivery services
- 📱 Phone IMEI registration guides
- 💰 Utility payment instructions

## Quick Start

### Environment Variables

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `BOT_TOKEN` | Yes | - | Your Telegram Bot token |
| `HEALTH_PORT` | No | `8080` | Health check port |
| `DEBUG` | No | `false` | Enable debug logging (for development) |

### Run with Go

```bash
# Production (clean logs)
export BOT_TOKEN="YOUR_BOT_TOKEN"
go run cmd/bot/main.go

# Development (with debug logging)
export BOT_TOKEN="YOUR_BOT_TOKEN"
export DEBUG=true
go run cmd/bot/main.go
```

### Run with Docker

```bash
# Production
docker run -e BOT_TOKEN="YOUR_TOKEN" -p 8080:8080 ekpa-bot

# Development with debug logs
docker run -e BOT_TOKEN="YOUR_TOKEN" -e DEBUG=true -p 8080:8080 ekpa-bot
```

## For Developers

### Content Structure

Content is in YAML files under `content/`. Each screen has:
- Text (MarkdownV2 format)
- Buttons (callback or URL)
- Navigation rules
- Auto-generated back/menu buttons

### Architecture

```
cmd/bot/          - Entry point
internal/domain/  - Core logic (screens, buttons, navigation)
internal/handler/ - Command and callback handlers
internal/service/ - Bot service
content/          - YAML content files
```

### Adding Content

1. Edit YAML files in `content/`
2. Restart bot (no rebuild needed)

### Running Tests

```bash
go test ./...
```

## Tech Stack

- Go 1.26+ with [telego](https://github.com/mymmrac/telego)
- YAML for content
- Docker & GitHub Actions
- Health check endpoint on :8080

## Contributing

Issues and PRs welcome! To update content, just edit YAML files.

## License

MIT License

---

**Use the bot:** [@EkpaAntalyaBot](https://t.me/EkpaAntalyaBot)  
**Questions:** [@qeeqez](https://t.me/qeeqez)

Made with ❤️ for EKPA 1207 community
