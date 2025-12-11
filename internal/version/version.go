package version

import "fmt"

var (
	// Version of the bot
	Version = "2.0.0"

	// GitCommit is the git commit hash
	GitCommit = "dev"

	// BuildDate is the build date
	BuildDate = "unknown"

	// GoVersion is the Go version used to build
	GoVersion = "unknown"
)

// Info returns formatted version information
func Info() string {
	return fmt.Sprintf("Ekpa Antalya Bot v%s (commit: %s, built: %s, go: %s)",
		Version, GitCommit, BuildDate, GoVersion)
}

// Short returns short version string
func Short() string {
	return fmt.Sprintf("v%s", Version)
}
