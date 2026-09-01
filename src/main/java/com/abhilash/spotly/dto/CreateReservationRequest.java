package com.abhilash.spotly.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
