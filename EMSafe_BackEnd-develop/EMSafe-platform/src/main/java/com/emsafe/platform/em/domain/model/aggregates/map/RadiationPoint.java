package com.emsafe.platform.em.domain.model.aggregates.map;

import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "radiation_point")
public class RadiationPoint extends AbstractAggregateRoot<RadiationPoint> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String level;  // Crítico, Alto, Medio, Bajo

    @Column(nullable = false)
    private String color;  // Color en formato HEX (#RRGGBB)

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double radiationValue;  // Valor numérico de la radiación

    @Column
    private String unit;  // Unidad de medida (Sv/h, mSv/h, etc.)

    @Column(name = "device_id")
    private Long deviceId;  // ID del dispositivo que registró este punto

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRadiationValue() {
        return radiationValue;
    }

    public void setRadiationValue(Double radiationValue) {
        this.radiationValue = radiationValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
} 