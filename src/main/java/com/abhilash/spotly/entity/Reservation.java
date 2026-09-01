package com.abhilash.spotly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Reservation {

    public Reservation(User user, Slot slot) {
        this.user = user;
        this.slot = slot;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;
}
