package com.emsafe.platform.em.application.mapimpl;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import com.emsafe.platform.em.domain.services.map.MapService;
import com.emsafe.platform.em.infrastructure.persistance.jpa.map.RadiationPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MapServiceImpl implements MapService {

    private final RadiationPointRepository radiationPointRepository;

    @Override
    public RadiationPoint addRadiationPoint(RadiationPoint point) {
        return radiationPointRepository.save(point);
    }

    @Override
    public List<RadiationPoint> getAllPoints() {
        return radiationPointRepository.findAll();
    }

    @Override
    public List<RadiationPoint> getPointsByDevice(Long deviceId) {
        return radiationPointRepository.findByDeviceId(deviceId);
    }

    @Override
    public List<RadiationPoint> getPointsInBounds(Double minLat, Double maxLat, Double minLon, Double maxLon) {
        return radiationPointRepository.findPointsInBounds(minLat, maxLat, minLon, maxLon);
    }

    @Override
    public List<RadiationPoint> getPointsByLevel(String level) {
        return radiationPointRepository.findByLevel(level);
    }

    @Override
    public void deletePoint(Long pointId) {
        radiationPointRepository.deleteById(pointId);
    }

    @Override
    public RadiationPoint updatePoint(Long pointId, RadiationPoint point) {
        RadiationPoint existingPoint = radiationPointRepository.findById(pointId)
            .orElseThrow(() -> new EntityNotFoundException("Punto de radiación no encontrado con ID: " + pointId));
        
        existingPoint.setLatitude(point.getLatitude());
        existingPoint.setLongitude(point.getLongitude());
        existingPoint.setLevel(point.getLevel());
        existingPoint.setColor(point.getColor());
        existingPoint.setDescription(point.getDescription());
        existingPoint.setRadiationValue(point.getRadiationValue());
        existingPoint.setUnit(point.getUnit());
        existingPoint.setDeviceId(point.getDeviceId());
        
        return radiationPointRepository.save(existingPoint);
    }
} 