package com.emsafe.platform.em.infrastructure.persistance.jpa.map;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadiationPointRepository extends JpaRepository<RadiationPoint, Long> {
    
    @Query("SELECT r FROM RadiationPoint r WHERE r.latitude BETWEEN :minLat AND :maxLat AND r.longitude BETWEEN :minLon AND :maxLon")
    List<RadiationPoint> findPointsInBounds(
        @Param("minLat") Double minLat,
        @Param("maxLat") Double maxLat,
        @Param("minLon") Double minLon,
        @Param("maxLon") Double maxLon
    );

    List<RadiationPoint> findByDeviceId(Long deviceId);
    List<RadiationPoint> findByLevel(String level);

    @Query("SELECT r FROM RadiationPoint r WHERE r.latitude BETWEEN :minLat AND :maxLat AND r.longitude BETWEEN :minLon AND :maxLon")
    List<RadiationPoint> findByLatitudeBetweenAndLongitudeBetween(
        @Param("minLat") Double minLat,
        @Param("maxLat") Double maxLat,
        @Param("minLon") Double minLon,
        @Param("maxLon") Double maxLon
    );
} 