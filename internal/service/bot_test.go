package service_test

import (
	"context"
	"testing"
	"time"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/service"
)

func TestProcessUpdatesReturnsWhenChannelCloses(t *testing.T) {
	svc := &service.BotService{}
	updates := make(chan telego.Update)
	close(updates)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()

	err := svc.ProcessUpdates(ctx, updates)
	if err == nil {
		t.Fatal("expected an error when updates channel closes")
	}
	if err.Error() != "updates channel closed" {
		t.Fatalf("unexpected error: %v", err)
	}
}
