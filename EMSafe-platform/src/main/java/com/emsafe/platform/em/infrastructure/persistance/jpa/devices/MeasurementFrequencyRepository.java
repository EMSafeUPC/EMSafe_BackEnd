package com.emsafe.platform.em.infrastructure.persistance.jpa.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.MeasurementFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementFrequencyRepository extends JpaRepository<MeasurementFrequency, Long> {
}
