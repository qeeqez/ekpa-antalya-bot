package domain_test

import "testing"

import "github.com/qeeqez/ekpaantalyabot/internal/domain"

const (
	messageTestID     = "TEST"
	messageTestText   = "Test"
	messageButtonID   = "btn1"
	messageButtonText = "Button"
)

func TestScreenValidation(t *testing.T) {
	tests := []struct {
		name    string
		screen  domain.Screen
		wantErr bool
	}{
		{
			name: "valid screen",
			screen: domain.Screen{
				ID:        messageTestID,
				Text:      "Test message",
				ParseMode: domain.ParseModeMarkdownV2,
				InlineKeyboard: domain.InlineKeyboard{
					Rows: []domain.ButtonRow{
						{
							Buttons: []domain.Button{
								{
									ID:           messageButtonID,
									Text:         messageButtonText,
									Type:         domain.ButtonTypeCallback,
									CallbackData: messageTestID,
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
			screen: domain.Screen{
				ID:   "",
				Text: messageTestText,
			},
			wantErr: true,
		},
		{
			name: "empty text",
			screen: domain.Screen{
				ID:   messageTestID,
				Text: "",
			},
			wantErr: true,
		},
		{
			name: "invalid button",
			screen: domain.Screen{
				ID:   messageTestID,
				Text: messageTestText,
				InlineKeyboard: domain.InlineKeyboard{
					Rows: []domain.ButtonRow{
						{
							Buttons: []domain.Button{
								{
									ID:   messageButtonID,
									Text: "", // Invalid: empty text
									Type: domain.ButtonTypeCallback,
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
	screen := domain.Screen{
		ID:   messageTestID,
		Text: messageTestText,
		NavigationTargets: []domain.Navigation{
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
