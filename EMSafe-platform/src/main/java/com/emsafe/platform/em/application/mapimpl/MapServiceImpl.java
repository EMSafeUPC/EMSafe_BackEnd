package com.emsafe.platform.em.application.mapimpl;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import com.emsafe.platform.em.domain.services.map.MapService;
import com.emsafe.platform.em.infrastructure.persistance.jpa.map.RadiationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private RadiationPointRepository radiationPointRepository;

    @Override
    @Transactional
    public RadiationPoint savePoint(RadiationPoint point) {
        // Si es un nuevo punto, asegurarse que el ID sea null
        if (point.getId() == null) {
            point = radiationPointRepository.save(point);
        } else {
            // Si es una actualización, verificar que el punto existe
            if (!radiationPointRepository.existsById(point.getId())) {
                throw new RuntimeException("No se encontró el punto con ID: " + point.getId());
            }
            point = radiationPointRepository.save(point);
        }
        return point;
    }

    @Override
    public List<RadiationPoint> getAllPoints() {
        return radiationPointRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePoint(Long id) {
        radiationPointRepository.deleteById(id);
    }

    @Override
    public List<RadiationPoint> findPointsInBounds(Double minLat, Double maxLat, Double minLon, Double maxLon) {
        return radiationPointRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLon, maxLon);
    }

    @Override
    public List<RadiationPoint> findPointsByDevice(Long deviceId) {
        return radiationPointRepository.findByDeviceId(deviceId);
    }

    @Override
    public List<RadiationPoint> findPointsByLevel(String level) {
        return radiationPointRepository.findByLevel(level);
    }

    @Override
    public RadiationPoint findById(Long id) {
        return radiationPointRepository.findById(id).orElse(null);
    }
} 
