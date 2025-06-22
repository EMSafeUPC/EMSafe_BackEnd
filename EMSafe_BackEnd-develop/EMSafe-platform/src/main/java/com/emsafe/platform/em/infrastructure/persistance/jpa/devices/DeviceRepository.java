package com.emsafe.platform.em.infrastructure.persistance.jpa.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}