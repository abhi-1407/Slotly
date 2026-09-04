package com.abhilash.spotly.service;

import org.springframework.stereotype.Service;

import com.abhilash.spotly.dto.CreateSlotRequest;
import com.abhilash.spotly.entity.Resource;
import com.abhilash.spotly.entity.Slot;
import com.abhilash.spotly.exception.ResourceNotFoundException;
import com.abhilash.spotly.repository.ResourceRepository;
import com.abhilash.spotly.repository.SlotRepository;

@Service
public class SlotService {
    private final SlotRepository slotRepository;
    private final ResourceRepository resourceRepository;

    public SlotService(SlotRepository slotRepository, ResourceRepository resourceRepository) {
        this.slotRepository = slotRepository;
        this.resourceRepository = resourceRepository;
    }

    public Slot createSlot(CreateSlotRequest request) {
        Resource resource = resourceRepository.findById(request.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException());
        Slot slot = new Slot();
        slot.setResource(resource);
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setBooked(false);
        return slotRepository.save(slot);
    }
}
