package health

import (
	"encoding/json"
	"log"
	"net/http"
	"sync/atomic"
	"time"

	"github.com/qeeqez/ekpaantalyabot/internal/version"
)

// Status represents the health status of the bot
type Status struct {
	Status      string    `json:"status"`
	Version     string    `json:"version"`
	Uptime      string    `json:"uptime"`
	LastUpdate  time.Time `json:"last_update"`
	UpdateCount int64     `json:"update_count"`
}

// Checker manages health check status
type Checker struct {
	startTime   time.Time
	lastUpdate  atomic.Value
	updateCount atomic.Int64
}

// NewChecker creates a new health checker
func NewChecker() *Checker {
	checker := &Checker{
		startTime: time.Now(),
	}
	checker.lastUpdate.Store(time.Now())
	return checker
}

// RecordUpdate records that an update was processed
func (c *Checker) RecordUpdate() {
	c.lastUpdate.Store(time.Now())
	c.updateCount.Add(1)
}

// GetStatus returns current health status
func (c *Checker) GetStatus() Status {
	lastUpdate := c.lastUpdate.Load().(time.Time)
	return Status{
		Status:      "healthy",
		Version:     version.Short(),
		Uptime:      time.Since(c.startTime).String(),
		LastUpdate:  lastUpdate,
		UpdateCount: c.updateCount.Load(),
	}
}

// Handler returns an HTTP handler for health checks
func (c *Checker) Handler() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		status := c.GetStatus()
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusOK)
		if err := json.NewEncoder(w).Encode(status); err != nil {
			log.Printf("Error encoding health status: %v", err)
		}
	}
}

// StartServer starts the health check HTTP server
func (c *Checker) StartServer(addr string) error {
	mux := http.NewServeMux()
	mux.HandleFunc("/health", c.Handler())
	mux.HandleFunc("/healthz", c.Handler())
	mux.HandleFunc("/ready", c.Handler())

	log.Printf("Starting health check server on %s", addr)
	return http.ListenAndServe(addr, mux)
}
