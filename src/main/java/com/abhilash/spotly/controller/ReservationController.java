package com.abhilash.spotly.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhilash.spotly.entity.Reservation;
import com.abhilash.spotly.dto.CreateReservationRequest;
import com.abhilash.spotly.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody @Valid CreateReservationRequest createReservationRequest) {
        return reservationService.createReservation(createReservationRequest);
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable UUID id) {
        return reservationService.getReservationById(id);
    }
}   