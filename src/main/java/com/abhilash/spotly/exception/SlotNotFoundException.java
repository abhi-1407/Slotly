package com.abhilash.spotly.exception;

public class SlotNotFoundException extends RuntimeException {
    public SlotNotFoundException() {
        super("Slot not found");
    }
}
