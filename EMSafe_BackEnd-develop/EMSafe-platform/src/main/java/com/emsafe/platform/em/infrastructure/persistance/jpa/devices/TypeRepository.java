package com.emsafe.platform.em.infrastructure.persistance.jpa.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
