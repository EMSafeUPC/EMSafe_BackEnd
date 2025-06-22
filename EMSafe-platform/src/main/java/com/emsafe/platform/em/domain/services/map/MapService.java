package com.emsafe.platform.em.domain.services.map;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import java.util.List;

public interface MapService {
    List<RadiationPoint> getAllPoints();
    RadiationPoint savePoint(RadiationPoint point);
    void deletePoint(Long id);
    List<RadiationPoint> findPointsInBounds(Double minLat, Double maxLat, Double minLon, Double maxLon);
    List<RadiationPoint> findPointsByDevice(Long deviceId);
    List<RadiationPoint> findPointsByLevel(String level);
    RadiationPoint findById(Long id);
} 
