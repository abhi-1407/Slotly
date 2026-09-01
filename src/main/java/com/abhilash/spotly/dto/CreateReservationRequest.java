package com.abhilash.spotly.dto;

import java.util.UUID;

public class CreateReservationRequest {
    private UUID userId;
    private UUID slotId;

    public UUID getUserId() {
        return userId;
    }

    public UUID getSlotId() {
        return slotId;
    }
}
