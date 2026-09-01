package com.abhilash.spotly.repository;

import com.abhilash.spotly.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot, UUID> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Slot> findById(UUID id);
}
