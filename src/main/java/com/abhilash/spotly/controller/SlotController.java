package com.abhilash.spotly.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhilash.spotly.dto.CreateSlotRequest;
import com.abhilash.spotly.entity.Slot;
import com.abhilash.spotly.service.SlotService;

@RestController
@RequestMapping("/slots")
public class SlotController {
    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public Slot createSlot(@RequestBody CreateSlotRequest createSlotRequest) {
        return slotService.createSlot(createSlotRequest);
    }
}
