package middleware

import (
	"log"
	"runtime/debug"
)

// RecoverPanic recovers from panics and logs the error
func RecoverPanic() {
	if r := recover(); r != nil {
		log.Printf("Recovered from panic: %v\n%s", r, debug.Stack())
	}
}
