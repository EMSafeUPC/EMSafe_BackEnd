package com.emsafe.platform.em.domain.model.aggregates.map;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "radiation_point")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RadiationPoint {
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
} 