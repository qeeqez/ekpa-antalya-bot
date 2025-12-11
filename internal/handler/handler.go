package handler

import (
	"context"

	"github.com/mymmrac/telego"
)

// Handler defines the interface for update handlers
type Handler interface {
	// Supports checks if this handler can process the update
	Supports(update telego.Update) bool

	// Priority returns the priority of this handler (lower values = higher priority)
	Priority() int

	// Handle processes the update
	Handle(ctx context.Context, update telego.Update) error
}

// Chain represents a chain of handlers
type Chain struct {
	handlers []Handler
}

// NewChain creates a new handler chain
func NewChain(handlers ...Handler) *Chain {
	return &Chain{
		handlers: handlers,
	}
}

// Handle processes an update through the handler chain
func (c *Chain) Handle(ctx context.Context, update telego.Update) error {
	// Find the first handler that supports this update
	for _, handler := range c.handlers {
		if handler.Supports(update) {
			return handler.Handle(ctx, update)
		}
	}
	return nil
}

// AddHandler adds a handler to the chain
func (c *Chain) AddHandler(handler Handler) {
	c.handlers = append(c.handlers, handler)
}
