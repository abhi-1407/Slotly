package com.abhilash.spotly.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateReservationRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID slotId;

    public UUID getUserId() {
        return userId;
    }

    public UUID getSlotId() {
        return slotId;
    }
}
