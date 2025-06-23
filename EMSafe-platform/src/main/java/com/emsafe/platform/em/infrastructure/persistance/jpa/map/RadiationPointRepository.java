package com.emsafe.platform.em.infrastructure.persistance.jpa.map;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadiationPointRepository extends JpaRepository<RadiationPoint, Long> {
    List<RadiationPoint> findByLatitudeBetweenAndLongitudeBetween(Double minLat, Double maxLat, Double minLon, Double maxLon);
    List<RadiationPoint> findByDeviceId(Long deviceId);
    List<RadiationPoint> findByLevel(String level);
}
