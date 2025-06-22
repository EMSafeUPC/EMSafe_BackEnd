package com.emsafe.platform.em.domain.services.map;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;

import java.util.List;

public interface MapService {
    RadiationPoint addRadiationPoint(RadiationPoint point);
    List<RadiationPoint> getAllPoints();
    List<RadiationPoint> getPointsByDevice(Long deviceId);
    List<RadiationPoint> getPointsInBounds(Double minLat, Double maxLat, Double minLon, Double maxLon);
    List<RadiationPoint> getPointsByLevel(String level);
    void deletePoint(Long pointId);
    RadiationPoint updatePoint(Long pointId, RadiationPoint point);
} 