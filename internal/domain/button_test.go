package domain_test

import "testing"

import "github.com/qeeqez/ekpaantalyabot/internal/domain"

const (
	buttonTestID       = "test"
	buttonTestText     = "Test"
	buttonCallbackData = "TEST_CALLBACK"
	buttonEmptyData    = "TEST"
)

func TestButtonValidation(t *testing.T) {
	tests := []struct {
		name    string
		button  domain.Button
		wantErr bool
	}{
		{
			name: "valid callback button",
			button: domain.Button{
				ID:           buttonTestID,
				Text:         buttonTestText,
				Type:         domain.ButtonTypeCallback,
				CallbackData: buttonCallbackData,
			},
			wantErr: false,
		},
		{
			name: "valid URL button",
			button: domain.Button{
				ID:   buttonTestID,
				Text: buttonTestText,
				Type: domain.ButtonTypeURL,
				URL:  "https://example.com",
			},
			wantErr: false,
		},
		{
			name: "empty text",
			button: domain.Button{
				ID:           buttonTestID,
				Text:         "",
				Type:         domain.ButtonTypeCallback,
				CallbackData: buttonEmptyData,
			},
			wantErr: true,
		},
		{
			name: "callback without callback data",
			button: domain.Button{
				ID:   buttonTestID,
				Text: buttonTestText,
				Type: domain.ButtonTypeCallback,
			},
			wantErr: true,
		},
		{
			name: "URL without URL",
			button: domain.Button{
				ID:   buttonTestID,
				Text: buttonTestText,
				Type: domain.ButtonTypeURL,
			},
			wantErr: true,
		},
		{
			name: "unknown type",
			button: domain.Button{
				ID:   buttonTestID,
				Text: buttonTestText,
				Type: "unknown",
			},
			wantErr: true,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			err := tt.button.Validate()
			if (err != nil) != tt.wantErr {
				t.Errorf("Validate() error = %v, wantErr %v", err, tt.wantErr)
			}
		})
	}
}
