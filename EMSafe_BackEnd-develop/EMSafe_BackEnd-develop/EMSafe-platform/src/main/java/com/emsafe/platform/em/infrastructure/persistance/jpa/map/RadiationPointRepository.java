package com.emsafe.platform.em.infrastructure.persistance.jpa.map;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadiationPointRepository extends JpaRepository<RadiationPoint, Long> {
    List<RadiationPoint> findByDeviceId(Long deviceId);
    
    @Query("SELECT rp FROM RadiationPoint rp WHERE " +
           "rp.latitude BETWEEN :minLat AND :maxLat AND " +
           "rp.longitude BETWEEN :minLon AND :maxLon")
    List<RadiationPoint> findPointsInBounds(Double minLat, Double maxLat, Double minLon, Double maxLon);
    
    List<RadiationPoint> findByLevel(String level);
} 