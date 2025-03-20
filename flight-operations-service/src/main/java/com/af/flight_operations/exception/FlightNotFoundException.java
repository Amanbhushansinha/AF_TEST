package com.af.flight_operations.exception;

@SuppressWarnings("serial")
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
