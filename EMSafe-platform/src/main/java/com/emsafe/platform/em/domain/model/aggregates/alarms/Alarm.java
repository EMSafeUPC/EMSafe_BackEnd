// src/main/java/com/emsafe/platform/em/domain/model/aggregates/alarms/Alarm.java
package com.emsafe.platform.em.domain.model.aggregates.alarms;

import com.emsafe.platform.em.domain.model.aggregates.devices.Device;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "alarm")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Relación al dispositivo que dispara la alarma */
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_id")
    private Device device;

    private String type;
    private String level;
    private Double reading;
    private Double threshold;
    private String unit;

    private Instant timestamp;

    private Boolean acknowledged = false;
    private String acknowledgedBy;
    private Instant acknowledgedAt;

    private Boolean resolved = false;
    private Instant resolvedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    // getters/setters estándar

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Double getReading() { return reading; }
    public void setReading(Double reading) { this.reading = reading; }

    public Double getThreshold() { return threshold; }
    public void setThreshold(Double threshold) { this.threshold = threshold; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public Boolean getAcknowledged() { return acknowledged; }
    public void setAcknowledged(Boolean acknowledged) { this.acknowledged = acknowledged; }

    public String getAcknowledgedBy() { return acknowledgedBy; }
    public void setAcknowledgedBy(String acknowledgedBy) { this.acknowledgedBy = acknowledgedBy; }

    public Instant getAcknowledgedAt() { return acknowledgedAt; }
    public void setAcknowledgedAt(Instant acknowledgedAt) { this.acknowledgedAt = acknowledgedAt; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public Instant getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(Instant resolvedAt) { this.resolvedAt = resolvedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    /** Para exponer deviceId/deviceName en el JSON tal como tu ejemplo */
    @JsonProperty("deviceId")
    public Long getDeviceId() {
        return device != null ? device.getId() : null;
    }

    @JsonProperty("deviceName")
    public String getDeviceName() {
        return device != null ? device.getName() : null;
    }
}
