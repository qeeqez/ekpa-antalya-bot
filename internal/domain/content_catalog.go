package domain

// ContentCatalog is the root domain object for all bot content.
type ContentCatalog struct {
	Screens      map[string]*Screen
	Commands     map[string]*Command
	CommandOrder []string
	Fragments    map[string]map[string]string
}

// NewContentCatalog creates an empty content catalog.
func NewContentCatalog() *ContentCatalog {
	return &ContentCatalog{
		Screens:      make(map[string]*Screen),
		Commands:     make(map[string]*Command),
		CommandOrder: make([]string, 0),
		Fragments:    make(map[string]map[string]string),
	}
}
