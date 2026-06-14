package domain

// ContentCatalog is the root domain object for all bot content.
type ContentCatalog struct {
	Screens      map[string]*Screen
	Commands     map[string]*Command
	CommandOrder []string
	Bundles      map[string]*LocalizedBundle
}

// NewContentCatalog creates an empty content catalog.
func NewContentCatalog() *ContentCatalog {
	return &ContentCatalog{
		Screens:      make(map[string]*Screen),
		Commands:     make(map[string]*Command),
		CommandOrder: make([]string, 0),
		Bundles:      make(map[string]*LocalizedBundle),
	}
}

// LocalizedBundle groups all locale-specific overlays for a single locale.
type LocalizedBundle struct {
	Screens   map[string]ScreenLocale
	Commands  map[string]CommandLocale
	Fragments map[string]string
}

// NewLocalizedBundle creates an empty localized bundle.
func NewLocalizedBundle() *LocalizedBundle {
	return &LocalizedBundle{
		Screens:   make(map[string]ScreenLocale),
		Commands:  make(map[string]CommandLocale),
		Fragments: make(map[string]string),
	}
}

// Bundle returns the localized bundle for the locale, creating it if needed.
func (c *ContentCatalog) Bundle(localeCode string) *LocalizedBundle {
	if bundle, ok := c.Bundles[localeCode]; ok {
		return bundle
	}

	bundle := NewLocalizedBundle()
	c.Bundles[localeCode] = bundle
	return bundle
}
