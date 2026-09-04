package com.abhilash.spotly.exception;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException() {
        super("Slot already reserved");
    }
}
