package domain

import "fmt"

// DomainError represents a domain-level error
type DomainError struct {
	Message string
}

func (e DomainError) Error() string {
	return e.Message
}

// ErrInvalidButton creates a button validation error
func ErrInvalidButton(format string, args ...interface{}) error {
	return DomainError{Message: fmt.Sprintf("invalid button: "+format, args...)}
}

// ErrInvalidScreen creates a screen validation error
func ErrInvalidScreen(format string, args ...interface{}) error {
	return DomainError{Message: fmt.Sprintf("invalid screen: "+format, args...)}
}

// ErrScreenNotFound creates a screen not found error
func ErrScreenNotFound(screenID string) error {
	return DomainError{Message: fmt.Sprintf("screen not found: %s", screenID)}
}

// ErrInvalidCommand creates an invalid command error
func ErrInvalidCommand(command string) error {
	return DomainError{Message: fmt.Sprintf("invalid command: %s", command)}
}
