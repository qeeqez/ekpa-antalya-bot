package service

import (
	"context"
	"testing"
	"time"

	"github.com/mymmrac/telego"
)

func TestProcessUpdatesReturnsWhenChannelCloses(t *testing.T) {
	t.Parallel()

	svc := &BotService{}
	updates := make(chan telego.Update)
	close(updates)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()

	err := svc.processUpdates(ctx, updates)
	if err == nil {
		t.Fatal("expected an error when updates channel closes")
	}
	if err.Error() != "updates channel closed" {
		t.Fatalf("unexpected error: %v", err)
	}
}
