package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"sort"

	"github.com/qeeqez/ekpaantalyabot/internal/config"
	"github.com/qeeqez/ekpaantalyabot/internal/locale"
	"go.yaml.in/yaml/v3"
)

type localeStats struct {
	Fragments int
	Commands  int
	Screens   int
}

func main() {
	repo, err := config.NewContentRepository("content")
	if err != nil {
		writeError(os.Stderr, "failed to load content: ", err)
		os.Exit(1)
	}

	stats, repeated := collectLocaleStats("content/locales")
	issues := repo.AuditLocaleParity()

	out := bufio.NewWriter(os.Stdout)
	defer func() {
		_ = out.Flush()
	}()

	if err := writeLine(out, "Content audit"); err != nil {
		exitWithError(err)
	}
	if err := writef(out, "screens: %d\n", len(repo.GetAllScreens())); err != nil {
		exitWithError(err)
	}
	if err := writef(out, "commands: %d\n", len(repo.GetCommandsForLocale("ru").Commands)); err != nil {
		exitWithError(err)
	}
	if err := writeLine(out, "locale stats:"); err != nil {
		exitWithError(err)
	}

	locales := make([]localeProfileView, 0, len(stats))
	for _, profile := range locale.Profiles() {
		if _, ok := stats[profile.Code]; ok {
			locales = append(locales, localeProfileView{Code: profile.Code, Name: profile.Name})
		}
	}
	sort.SliceStable(locales, func(i, j int) bool { return locales[i].Code < locales[j].Code })
	for _, localeInfo := range locales {
		s := stats[localeInfo.Code]
		if err := writef(out, "  %s (%s): %d screens, %d commands, %d fragments\n", localeInfo.Code, localeInfo.Name, s.Screens, s.Commands, s.Fragments); err != nil {
			exitWithError(err)
		}
	}

	if len(repeated) > 0 {
		if err := writeLine(out, "repeated strings:"); err != nil {
			exitWithError(err)
		}
		for _, item := range repeated {
			if err := writef(out, "  %d x %q\n", item.Count, item.Text); err != nil {
				exitWithError(err)
			}
		}
	}

	if len(issues) > 0 {
		if err := writeLine(out, "locale parity issues:"); err != nil {
			exitWithError(err)
		}
		for _, issue := range issues {
			if err := writef(out, "  - %s\n", issue); err != nil {
				exitWithError(err)
			}
		}
		if err := out.Flush(); err != nil {
			exitWithError(err)
		}
		exitWithError(fmt.Errorf("content audit failed with %d locale parity issue(s)", len(issues)))
	}
}

type localeProfileView struct {
	Code string
	Name string
}

func writeLine(w io.Writer, text string) error {
	_, err := io.WriteString(w, text+"\n")
	return err
}

func writef(w io.Writer, format string, args ...any) error {
	_, err := io.WriteString(w, fmt.Sprintf(format, args...))
	return err
}

func exitWithError(err error) {
	writeError(os.Stderr, "", err)
	os.Exit(1)
}

func writeError(w io.Writer, prefix string, err error) {
	_, _ = io.WriteString(w, prefix+err.Error()+"\n")
}

type repeatedString struct {
	Text  string
	Count int
}

func collectLocaleStats(localesDir string) (map[string]localeStats, []repeatedString) {
	stats := make(map[string]localeStats)
	counts := make(map[string]int)

	entries, err := os.ReadDir(localesDir)
	if err != nil {
		return stats, nil
	}

	for _, entry := range entries {
		if !entry.IsDir() {
			continue
		}

		localeCode := entry.Name()
		localeDir := filepath.Join(localesDir, localeCode)
		localeStat := localeStats{}

		localeEntries, err := os.ReadDir(localeDir)
		if err != nil {
			continue
		}

		for _, file := range localeEntries {
			if filepath.Ext(file.Name()) != ".yaml" {
				continue
			}

			data, err := os.ReadFile(filepath.Join(localeDir, file.Name()))
			if err != nil {
				continue
			}

			localeStat = addFileStats(data, localeStat, counts)
		}

		stats[localeCode] = localeStat
	}

	repeated := make([]repeatedString, 0)
	for text, count := range counts {
		if count > 1 {
			repeated = append(repeated, repeatedString{Text: text, Count: count})
		}
	}
	sort.Slice(repeated, func(i, j int) bool {
		if repeated[i].Count == repeated[j].Count {
			return repeated[i].Text < repeated[j].Text
		}
		return repeated[i].Count > repeated[j].Count
	})

	if len(repeated) > 10 {
		repeated = repeated[:10]
	}

	return stats, repeated
}

func addFileStats(data []byte, stat localeStats, counts map[string]int) localeStats {
	var content struct {
		Fragments map[string]string `yaml:"fragments"`
		Commands  []struct {
			Description string `yaml:"description"`
		} `yaml:"commands"`
		Screens []struct {
			Text        string            `yaml:"text"`
			ButtonTexts map[string]string `yaml:"button_texts"`
		} `yaml:"screens"`
	}

	if err := yaml.Unmarshal(data, &content); err != nil {
		return stat
	}

	stat.Fragments += len(content.Fragments)
	stat.Commands += len(content.Commands)
	stat.Screens += len(content.Screens)

	for _, cmd := range content.Commands {
		if cmd.Description != "" {
			counts[cmd.Description]++
		}
	}
	for _, screen := range content.Screens {
		if screen.Text != "" {
			counts[screen.Text]++
		}
		for _, text := range screen.ButtonTexts {
			if text != "" {
				counts[text]++
			}
		}
	}

	return stat
}
