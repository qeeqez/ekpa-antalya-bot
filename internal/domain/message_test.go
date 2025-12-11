package domain

import "testing"

func TestScreenValidation(t *testing.T) {
	tests := []struct {
		name    string
		screen  Screen
		wantErr bool
	}{
		{
			name: "valid screen",
			screen: Screen{
				ID:        "TEST",
				Text:      "Test message",
				ParseMode: ParseModeMarkdownV2,
				InlineKeyboard: InlineKeyboard{
					Rows: []ButtonRow{
						{
							Buttons: []Button{
								{
									ID:           "btn1",
									Text:         "Button",
									Type:         ButtonTypeCallback,
									CallbackData: "TEST",
								},
							},
						},
					},
				},
			},
			wantErr: false,
		},
		{
			name: "empty ID",
			screen: Screen{
				ID:   "",
				Text: "Test",
			},
			wantErr: true,
		},
		{
			name: "empty text",
			screen: Screen{
				ID:   "TEST",
				Text: "",
			},
			wantErr: true,
		},
		{
			name: "invalid button",
			screen: Screen{
				ID:   "TEST",
				Text: "Test",
				InlineKeyboard: InlineKeyboard{
					Rows: []ButtonRow{
						{
							Buttons: []Button{
								{
									ID:   "btn1",
									Text: "", // Invalid: empty text
									Type: ButtonTypeCallback,
								},
							},
						},
					},
				},
			},
			wantErr: true,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			err := tt.screen.Validate()
			if (err != nil) != tt.wantErr {
				t.Errorf("Validate() error = %v, wantErr %v", err, tt.wantErr)
			}
		})
	}
}

func TestGetNavigationTarget(t *testing.T) {
	screen := Screen{
		ID:   "TEST",
		Text: "Test",
		NavigationTargets: []Navigation{
			{Callback: "BTN1", Target: "TARGET1"},
			{Callback: "BTN2", Target: "TARGET2"},
		},
	}

	tests := []struct {
		callback string
		want     string
		wantOk   bool
	}{
		{"BTN1", "TARGET1", true},
		{"BTN2", "TARGET2", true},
		{"BTN3", "", false},
	}

	for _, tt := range tests {
		t.Run(tt.callback, func(t *testing.T) {
			got, ok := screen.GetNavigationTarget(tt.callback)
			if ok != tt.wantOk {
				t.Errorf("GetNavigationTarget() ok = %v, want %v", ok, tt.wantOk)
			}
			if got != tt.want {
				t.Errorf("GetNavigationTarget() target = %v, want %v", got, tt.want)
			}
		})
	}
}
