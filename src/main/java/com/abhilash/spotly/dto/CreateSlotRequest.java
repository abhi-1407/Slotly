package com.abhilash.spotly.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateSlotRequest {
    private Long resourceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
