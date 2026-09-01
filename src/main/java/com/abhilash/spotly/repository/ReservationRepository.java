package com.abhilash.spotly.repository;

import com.abhilash.spotly.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    boolean existsBySlotId(UUID slotId);
}
