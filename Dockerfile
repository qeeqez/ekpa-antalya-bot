# Build stage
FROM golang:1.26.4-alpine AS builder

WORKDIR /build

# Copy go mod files
COPY go.mod go.sum ./
RUN go mod download

# Copy source code
COPY . .

# Build the application
RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o ekpabot cmd/bot/main.go

# Final stage
FROM alpine:latest

RUN apk --no-cache add ca-certificates tzdata

WORKDIR /app

# Copy binary from builder
COPY --from=builder /build/ekpabot .

# Copy content files
COPY content/ ./content/

# Run as non-root user
RUN adduser -D -u 1000 botuser
USER botuser

# Run the bot
CMD ["./ekpabot"]
