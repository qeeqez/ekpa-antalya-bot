package service_test

import (
	"context"
	"errors"
	"testing"
	"time"

	"github.com/mymmrac/telego"
	"github.com/qeeqez/ekpaantalyabot/internal/service"
)

func TestShouldRetryPollingError(t *testing.T) {
	tests := []struct {
		name string
		err  error
		want bool
	}{
		{name: "timeout", err: errors.New("lookup api.telegram.org: i/o timeout"), want: true},
		{name: "bad request", err: errors.New("telego: getUpdates: bad request"), want: false},
		{name: "unauthorized", err: errors.New("401 Unauthorized"), want: false},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := service.ShouldRetryPollingError(tt.err); got != tt.want {
				t.Fatalf("expected %v, got %v", tt.want, got)
			}
		})
	}
}

func TestNextRetryDelay(t *testing.T) {
	if got := service.NextRetryDelay(time.Second, 8*time.Second); got != 2*time.Second {
		t.Fatalf("expected 2s, got %v", got)
	}

	if got := service.NextRetryDelay(20*time.Second, 8*time.Second); got != 8*time.Second {
		t.Fatalf("expected cap at 8s, got %v", got)
	}
}

func TestProcessUpdatesReturnsSentinelOnChannelClose(t *testing.T) {
	svc := &service.BotService{}
	updates := make(chan telego.Update)
	close(updates)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()

	err := svc.ProcessUpdates(ctx, updates)
	if !errors.Is(err, service.ErrUpdatesChannelClosed) {
		t.Fatalf("expected sentinel error, got %v", err)
	}
}
