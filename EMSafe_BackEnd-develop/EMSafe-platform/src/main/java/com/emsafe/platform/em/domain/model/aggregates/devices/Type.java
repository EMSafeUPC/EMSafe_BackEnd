package com.emsafe.platform.em.domain.model.aggregates.devices;

import jakarta.persistence.*;

@Entity
@Table(name = "device_type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Ej: "Celular", "Televisor", etc.

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
