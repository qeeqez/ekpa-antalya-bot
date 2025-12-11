package domain

// NavigationHierarchy defines the navigation structure and parent-child relationships
type NavigationHierarchy struct {
	Parent string // Parent screen ID (auto-detected)
	Level  int    // 0 = main menu, 1+ = calculated from parent chain
}

// ScreenWithNavigation wraps a screen with automatic navigation
type ScreenWithNavigation struct {
	*Screen
	Hierarchy NavigationHierarchy
}

// NavigationRegistry manages the navigation hierarchy
type NavigationRegistry struct {
	hierarchy map[string]NavigationHierarchy
	screens   map[string]*Screen // Reference to all screens
}

// NewNavigationRegistry creates a new navigation registry
func NewNavigationRegistry() *NavigationRegistry {
	return &NavigationRegistry{
		hierarchy: make(map[string]NavigationHierarchy),
		screens:   make(map[string]*Screen),
	}
}

// BuildFromScreens automatically builds the navigation hierarchy from screen navigation rules
func (r *NavigationRegistry) BuildFromScreens(screens map[string]*Screen) {
	r.screens = screens

	// First pass: identify MAIN_MENU (level 0)
	for screenID := range screens {
		if screenID == "MAIN_MENU" {
			r.hierarchy[screenID] = NavigationHierarchy{
				Parent: "",
				Level:  0,
			}
			break
		}
	}

	// Build reverse mapping: which screens can navigate to which targets
	childToParents := make(map[string][]string)
	for screenID, screen := range screens {
		for _, nav := range screen.NavigationTargets {
			// nav.Target is where this screen can navigate to
			// So screenID is a potential parent of nav.Target
			childToParents[nav.Target] = append(childToParents[nav.Target], screenID)
		}
	}

	// Build hierarchy by traversing from known screens
	visited := make(map[string]bool)
	visited["MAIN_MENU"] = true

	// Keep processing until we've visited all screens
	changed := true
	for changed {
		changed = false
		for childID, parents := range childToParents {
			if visited[childID] {
				continue
			}

			// Find the first visited parent (closest to main menu)
			for _, parentID := range parents {
				if visited[parentID] {
					parentHierarchy := r.hierarchy[parentID]

					r.hierarchy[childID] = NavigationHierarchy{
						Parent: parentID,
						Level:  parentHierarchy.Level + 1,
					}
					visited[childID] = true
					changed = true
					break
				}
			}
		}
	}
}

// Register registers a screen in the navigation hierarchy (for manual override)
func (r *NavigationRegistry) Register(screenID string, hierarchy NavigationHierarchy) {
	r.hierarchy[screenID] = hierarchy
}

// GetHierarchy returns the hierarchy for a screen
func (r *NavigationRegistry) GetHierarchy(screenID string) (NavigationHierarchy, bool) {
	h, ok := r.hierarchy[screenID]
	return h, ok
}

// AddAutoNavigation adds automatic navigation buttons to a screen
func (r *NavigationRegistry) AddAutoNavigation(screen *Screen) *Screen {
	// Don't modify original screen
	enhanced := *screen
	enhanced.InlineKeyboard.Rows = make([]ButtonRow, len(screen.InlineKeyboard.Rows))
	copy(enhanced.InlineKeyboard.Rows, screen.InlineKeyboard.Rows)

	// Get hierarchy
	hierarchy, exists := r.GetHierarchy(screen.ID)
	if !exists {
		// No hierarchy defined, return as-is
		return &enhanced
	}

	// Main menu doesn't need navigation buttons
	if hierarchy.Level == 0 {
		return &enhanced
	}

	// Create navigation row
	var navButtons []Button

	// Add back button only if parent is not MAIN_MENU
	if hierarchy.Parent != "" && hierarchy.Parent != "MAIN_MENU" {
		backButton := Button{
			ID:           "auto_back",
			Text:         "◀️ Назад",
			Type:         ButtonTypeCallback,
			CallbackData: hierarchy.Parent + "_BUTTON",
		}
		navButtons = append(navButtons, backButton)
	}

	// Always add main menu button (except for main menu itself)
	mainMenuButton := Button{
		ID:           "auto_main_menu",
		Text:         "🏠 В главное меню",
		Type:         ButtonTypeCallback,
		CallbackData: "MAIN_MENU_BUTTON",
	}
	navButtons = append(navButtons, mainMenuButton)

	// Add navigation row at the bottom
	if len(navButtons) > 0 {
		navRow := ButtonRow{Buttons: navButtons}
		enhanced.InlineKeyboard.Rows = append(enhanced.InlineKeyboard.Rows, navRow)
	}

	return &enhanced
}

// ShouldAddNavigation checks if a screen should get auto navigation
func (r *NavigationRegistry) ShouldAddNavigation(screenID string) bool {
	hierarchy, exists := r.GetHierarchy(screenID)
	if !exists {
		return false
	}
	// Don't add navigation to main menu
	return hierarchy.Level > 0
}
