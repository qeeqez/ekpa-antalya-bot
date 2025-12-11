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
	r.initializeMainMenu()

	childToParents := r.buildChildToParentMapping(screens)
	r.buildHierarchyFromMapping(childToParents)
}

// initializeMainMenu sets up the main menu at level 0
func (r *NavigationRegistry) initializeMainMenu() {
	for screenID := range r.screens {
		if screenID == "MAIN_MENU" {
			r.hierarchy[screenID] = NavigationHierarchy{
				Parent: "",
				Level:  0,
			}
			break
		}
	}
}

// buildChildToParentMapping creates a reverse mapping of screen relationships
func (r *NavigationRegistry) buildChildToParentMapping(screens map[string]*Screen) map[string][]string {
	childToParents := make(map[string][]string)

	for screenID, screen := range screens {
		for _, nav := range screen.NavigationTargets {
			// nav.Target is where this screen can navigate to
			// So screenID is a potential parent of nav.Target
			childToParents[nav.Target] = append(childToParents[nav.Target], screenID)
		}
	}

	return childToParents
}

// buildHierarchyFromMapping builds the hierarchy by traversing from known screens
func (r *NavigationRegistry) buildHierarchyFromMapping(childToParents map[string][]string) {
	visited := make(map[string]bool)
	visited["MAIN_MENU"] = true

	// Keep processing until we've visited all screens
	for r.processHierarchyLevel(childToParents, visited) {
		// Continue until no more changes
	}
}

// processHierarchyLevel processes one level of hierarchy building
func (r *NavigationRegistry) processHierarchyLevel(childToParents map[string][]string, visited map[string]bool) bool {
	changed := false

	for childID, parents := range childToParents {
		if visited[childID] {
			continue
		}

		if r.assignParentToChild(childID, parents, visited) {
			visited[childID] = true
			changed = true
		}
	}

	return changed
}

// assignParentToChild finds and assigns the first visited parent to a child
func (r *NavigationRegistry) assignParentToChild(childID string, parents []string, visited map[string]bool) bool {
	for _, parentID := range parents {
		if visited[parentID] {
			parentHierarchy := r.hierarchy[parentID]
			r.hierarchy[childID] = NavigationHierarchy{
				Parent: parentID,
				Level:  parentHierarchy.Level + 1,
			}
			return true
		}
	}
	return false
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
	enhanced := r.cloneScreen(screen)

	hierarchy, exists := r.GetHierarchy(screen.ID)
	if !exists || hierarchy.Level == 0 {
		return enhanced
	}

	navButtons := r.buildNavigationButtons(hierarchy)
	if len(navButtons) > 0 {
		r.appendNavigationRow(enhanced, navButtons)
	}

	return enhanced
}

// cloneScreen creates a copy of the screen without modifying the original
func (r *NavigationRegistry) cloneScreen(screen *Screen) *Screen {
	enhanced := *screen
	enhanced.InlineKeyboard.Rows = make([]ButtonRow, len(screen.InlineKeyboard.Rows))
	copy(enhanced.InlineKeyboard.Rows, screen.InlineKeyboard.Rows)
	return &enhanced
}

// buildNavigationButtons creates navigation buttons based on hierarchy
func (r *NavigationRegistry) buildNavigationButtons(hierarchy NavigationHierarchy) []Button {
	var navButtons []Button

	// Add back button only if parent is not MAIN_MENU
	if r.shouldAddBackButton(hierarchy) {
		navButtons = append(navButtons, r.createBackButton(hierarchy.Parent))
	}

	// Always add main menu button (except for main menu itself)
	navButtons = append(navButtons, r.createMainMenuButton())

	return navButtons
}

// shouldAddBackButton checks if a back button should be added
func (r *NavigationRegistry) shouldAddBackButton(hierarchy NavigationHierarchy) bool {
	return hierarchy.Parent != "" && hierarchy.Parent != "MAIN_MENU"
}

// createBackButton creates a back button for the given parent
func (r *NavigationRegistry) createBackButton(parent string) Button {
	return Button{
		ID:           "auto_back",
		Text:         "◀️ Назад",
		Type:         ButtonTypeCallback,
		CallbackData: parent + "_BUTTON",
	}
}

// createMainMenuButton creates a main menu button
func (r *NavigationRegistry) createMainMenuButton() Button {
	return Button{
		ID:           "auto_main_menu",
		Text:         "🏠 В главное меню",
		Type:         ButtonTypeCallback,
		CallbackData: "MAIN_MENU_BUTTON",
	}
}

// appendNavigationRow adds a navigation row to the screen
func (r *NavigationRegistry) appendNavigationRow(screen *Screen, buttons []Button) {
	navRow := ButtonRow{Buttons: buttons}
	screen.InlineKeyboard.Rows = append(screen.InlineKeyboard.Rows, navRow)
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
