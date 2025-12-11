package health

import (
	"context"
	"encoding/json"
	"log"
	"net/http"
	"sync/atomic"
	"time"
)

// Status represents the health status of the bot
type Status struct {
	Status       string    `json:"status"`
	Uptime       string    `json:"uptime"`
	LastUpdate   time.Time `json:"last_update"`
	UpdateCount  int64     `json:"update_count"`
	ErrorCount   int64     `json:"error_count"`
	ContentLoads int64     `json:"content_loads"`
}

// Checker manages health check status
type Checker struct {
	startTime    time.Time
	lastUpdate   atomic.Value
	updateCount  atomic.Int64
	errorCount   atomic.Int64
	contentLoads atomic.Int64
	server       *http.Server
}

// NewChecker creates a new health checker
func NewChecker() *Checker {
	checker := &Checker{
		startTime: time.Now(),
	}
	checker.lastUpdate.Store(time.Now())
	return checker
}

// RecordUpdate records that an update was processed successfully
func (c *Checker) RecordUpdate() {
	c.lastUpdate.Store(time.Now())
	c.updateCount.Add(1)
}

// RecordError records that an error occurred
func (c *Checker) RecordError() {
	c.errorCount.Add(1)
}

// RecordContentLoad records that content was loaded
func (c *Checker) RecordContentLoad() {
	c.contentLoads.Add(1)
}

// GetStatus returns current health status
func (c *Checker) GetStatus() Status {
	lastUpdate := c.lastUpdate.Load().(time.Time)
	return Status{
		Status:       "healthy",
		Uptime:       time.Since(c.startTime).String(),
		LastUpdate:   lastUpdate,
		UpdateCount:  c.updateCount.Load(),
		ErrorCount:   c.errorCount.Load(),
		ContentLoads: c.contentLoads.Load(),
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

	c.server = &http.Server{
		Addr:    addr,
		Handler: mux,
	}

	log.Printf("Starting health check server on %s", addr)
	return c.server.ListenAndServe()
}

// Shutdown gracefully shuts down the health check server
func (c *Checker) Shutdown(ctx context.Context) error {
	if c.server == nil {
		return nil
	}
	log.Println("Shutting down health check server...")
	return c.server.Shutdown(ctx)
}
