package domain

import "testing"

func TestButtonValidation(t *testing.T) {
	tests := []struct {
		name    string
		button  Button
		wantErr bool
	}{
		{
			name: "valid callback button",
			button: Button{
				ID:           "test",
				Text:         "Test",
				Type:         ButtonTypeCallback,
				CallbackData: "TEST_CALLBACK",
			},
			wantErr: false,
		},
		{
			name: "valid URL button",
			button: Button{
				ID:   "test",
				Text: "Test",
				Type: ButtonTypeURL,
				URL:  "https://example.com",
			},
			wantErr: false,
		},
		{
			name: "empty text",
			button: Button{
				ID:           "test",
				Text:         "",
				Type:         ButtonTypeCallback,
				CallbackData: "TEST",
			},
			wantErr: true,
		},
		{
			name: "callback without callback data",
			button: Button{
				ID:   "test",
				Text: "Test",
				Type: ButtonTypeCallback,
			},
			wantErr: true,
		},
		{
			name: "URL without URL",
			button: Button{
				ID:   "test",
				Text: "Test",
				Type: ButtonTypeURL,
			},
			wantErr: true,
		},
		{
			name: "unknown type",
			button: Button{
				ID:   "test",
				Text: "Test",
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
