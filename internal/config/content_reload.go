package config

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log/slog"
	"os"
	"path/filepath"
	"sort"
	"strings"
	"time"

	"github.com/fsnotify/fsnotify"
	"github.com/qeeqez/ekpaantalyabot/internal/domain"
)

const contentReloadDebounce = 150 * time.Millisecond

// ReloadResult reports what changed during a content reload.
type ReloadResult struct {
	CommandsChanged bool
}

// Reload reloads content from disk and swaps in the new snapshot atomically.
func (r *ContentRepository) Reload() (ReloadResult, error) {
	fresh, err := loadContentRepository(r.contentDir)
	if err != nil {
		return ReloadResult{}, err
	}

	commandsChanged := commandSignature(r.catalog) != commandSignature(fresh.catalog)

	r.mu.Lock()
	defer r.mu.Unlock()

	r.catalog = fresh.catalog
	r.screens = fresh.screens
	r.navigation = fresh.navigation

	return ReloadResult{CommandsChanged: commandsChanged}, nil
}

// Watch starts a filesystem watcher for the content directory.
func (r *ContentRepository) Watch(ctx DoneContext, onReload func(ReloadResult, error)) error {
	watcher, err := fsnotify.NewWatcher()
	if err != nil {
		return fmt.Errorf("failed to create content watcher: %w", err)
	}

	dirs, err := collectContentWatchDirs(r.contentDir)
	if err != nil {
		_ = watcher.Close()
		return err
	}

	for _, dir := range dirs {
		if err := watcher.Add(dir); err != nil {
			_ = watcher.Close()
			return fmt.Errorf("failed to watch %s: %w", dir, err)
		}
	}

	slog.Info("Content watcher started", "directories", len(dirs))

	go r.runContentWatchLoop(ctx, watcher, onReload)

	return nil
}

// DoneContext is the subset of context.Context used by the watcher.
type DoneContext interface {
	Done() <-chan struct{}
}

func (r *ContentRepository) runContentWatchLoop(ctx DoneContext, watcher *fsnotify.Watcher, onReload func(ReloadResult, error)) {
	defer func() {
		_ = watcher.Close()
	}()

	scheduler := newContentReloadScheduler()

	for {
		select {
		case <-ctx.Done():
			scheduler.Stop()
			return
		case err, ok := <-watcher.Errors:
			if !ok {
				scheduler.Stop()
				return
			}
			reportContentWatchError(err, onReload)
		case event, ok := <-watcher.Events:
			if !ok {
				scheduler.Stop()
				return
			}

			if handleContentWatchEvent(event, watcher, onReload) {
				scheduler.Reset()
			}
		case <-scheduler.C():
			scheduler.Stop()
			reportContentReload(r, onReload)
		}
	}
}

func shouldReloadContent(event fsnotify.Event) bool {
	return event.Op&(fsnotify.Create|fsnotify.Write|fsnotify.Remove|fsnotify.Rename) != 0
}

func handleContentWatchEvent(event fsnotify.Event, watcher *fsnotify.Watcher, onReload func(ReloadResult, error)) bool {
	if !shouldReloadContent(event) {
		return false
	}

	if err := addWatchedDirsIfCreated(watcher, event); err != nil {
		reportContentWatchError(err, onReload)
	}

	return true
}

func reportContentWatchError(err error, onReload func(ReloadResult, error)) {
	if onReload != nil {
		onReload(ReloadResult{}, fmt.Errorf("content watcher error: %w", err))
	}
}

func reportContentReload(r *ContentRepository, onReload func(ReloadResult, error)) {
	result, err := r.Reload()
	if err != nil {
		if onReload != nil {
			onReload(ReloadResult{}, err)
		}
		return
	}

	if onReload != nil {
		onReload(result, nil)
	}
}

func collectContentWatchDirs(root string) ([]string, error) {
	dirs := make([]string, 0)
	if err := filepath.WalkDir(root, func(path string, d os.DirEntry, err error) error {
		if err != nil {
			return err
		}
		if d.IsDir() {
			dirs = append(dirs, path)
		}
		return nil
	}); err != nil {
		return nil, fmt.Errorf("failed to collect content watch directories: %w", err)
	}

	return dirs, nil
}

func addWatchedDirsIfCreated(watcher *fsnotify.Watcher, event fsnotify.Event) error {
	if event.Op&fsnotify.Create == 0 {
		return nil
	}

	info, err := os.Stat(event.Name)
	if err != nil || !info.IsDir() {
		return nil
	}

	return addWatchedDirs(watcher, event.Name)
}

func addWatchedDirs(watcher *fsnotify.Watcher, root string) error {
	dirs, err := collectContentWatchDirs(root)
	if err != nil {
		return err
	}

	for _, dir := range dirs {
		if err := watcher.Add(dir); err != nil {
			return fmt.Errorf("failed to watch %s: %w", dir, err)
		}
	}

	return nil
}

func commandSignature(catalog *domain.ContentCatalog) string {
	if catalog == nil {
		return ""
	}

	var parts []string
	parts = append(parts, strings.Join(catalog.CommandOrder, ","))

	shared := make([]string, 0, len(catalog.Commands))
	for name, command := range catalog.Commands {
		shared = append(shared, fmt.Sprintf("%s|%s|%s|%t", name, command.Command, command.ScreenID, command.IsPinned))
	}
	sort.Strings(shared)
	parts = append(parts, strings.Join(shared, ";"))

	localeCodes := make([]string, 0, len(catalog.Bundles))
	for localeCode := range catalog.Bundles {
		localeCodes = append(localeCodes, localeCode)
	}
	sort.Strings(localeCodes)

	for _, localeCode := range localeCodes {
		bundle := catalog.Bundles[localeCode]
		if bundle == nil {
			continue
		}

		localized := make([]string, 0, len(bundle.Commands))
		for commandName, command := range bundle.Commands {
			localized = append(localized, fmt.Sprintf("%s|%s", commandName, command.Description))
		}
		sort.Strings(localized)
		parts = append(parts, localeCode+":"+strings.Join(localized, ";"))
	}

	sum := sha256.Sum256([]byte(strings.Join(parts, "||")))
	return hex.EncodeToString(sum[:])
}

type contentReloadScheduler struct {
	timer  *time.Timer
	timerC <-chan time.Time
}

func newContentReloadScheduler() *contentReloadScheduler {
	return &contentReloadScheduler{}
}

func (s *contentReloadScheduler) Reset() {
	if s.timer == nil {
		s.timer = time.NewTimer(contentReloadDebounce)
		s.timerC = s.timer.C
		return
	}

	if !s.timer.Stop() {
		select {
		case <-s.timer.C:
		default:
		}
	}

	s.timer.Reset(contentReloadDebounce)
	s.timerC = s.timer.C
}

func (s *contentReloadScheduler) Stop() {
	if s.timer == nil {
		return
	}

	if !s.timer.Stop() {
		select {
		case <-s.timer.C:
		default:
		}
	}

	s.timer = nil
	s.timerC = nil
}

func (s *contentReloadScheduler) C() <-chan time.Time {
	return s.timerC
}
