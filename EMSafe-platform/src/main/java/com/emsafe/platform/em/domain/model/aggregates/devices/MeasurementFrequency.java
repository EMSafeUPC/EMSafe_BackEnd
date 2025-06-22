package com.emsafe.platform.em.domain.model.aggregates.devices;

import jakarta.persistence.*;

@Entity
@Table(name = "measurement_frequency")
public class MeasurementFrequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frequency; // Ej: "Diario", "Cada 6 horas", etc.

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
