package com.abhilash.spotly.service;

import com.abhilash.spotly.entity.Reservation;
import com.abhilash.spotly.entity.Slot;
import com.abhilash.spotly.entity.User;
import com.abhilash.spotly.repository.ReservationRepository;
import com.abhilash.spotly.repository.SlotRepository;
import com.abhilash.spotly.repository.UserRepository;

import java.util.UUID;

import com.abhilash.spotly.dto.CreateReservationRequest;
import com.abhilash.spotly.exception.SlotAlreadyBookedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final SlotRepository slotRepository;

        public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, SlotRepository slotRepository) {
            this.reservationRepository = reservationRepository;
            this.userRepository = userRepository;
            this.slotRepository = slotRepository;
    }

    @Transactional
    public Reservation createReservation(CreateReservationRequest createReservationRequest) {
        User user = userRepository.findById(createReservationRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Slot slot = slotRepository.findById(createReservationRequest.getSlotId()).orElseThrow(() -> new RuntimeException("Slot not found"));
        if(reservationRepository.existsBySlotId(createReservationRequest.getSlotId())) {
            throw new SlotAlreadyBookedException();
        }
        Reservation reservation = new Reservation(user, slot);
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }
}
