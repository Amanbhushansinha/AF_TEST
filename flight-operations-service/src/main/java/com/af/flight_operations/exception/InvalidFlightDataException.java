package com.af.flight_operations.exception;

@SuppressWarnings("serial")
public class InvalidFlightDataException extends RuntimeException {
    public InvalidFlightDataException(String message) {
        super(message);
    }
}