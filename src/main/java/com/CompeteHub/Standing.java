package com.CompeteHub;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Standing {
    @Id
    @GeneratedValue
    private Long id;

    private Long tourId;
    private byte[] standings;

    public Standing() {
    }

    public Standing(Long tourId, byte[] standings) {
        this.tourId = tourId;
        this.standings = standings;
    }
}
