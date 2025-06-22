package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import com.emsafe.platform.em.domain.services.map.MapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/map")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MapController {

    private final MapService mapService;

    @PostMapping("/points")
    public ResponseEntity<RadiationPoint> addPoint(@Valid @RequestBody RadiationPoint point) {
        return ResponseEntity.ok(mapService.addRadiationPoint(point));
    }

    @GetMapping("/points")
    public ResponseEntity<List<RadiationPoint>> getAllPoints() {
        return ResponseEntity.ok(mapService.getAllPoints());
    }

    @GetMapping("/points/device/{deviceId}")
    public ResponseEntity<List<RadiationPoint>> getPointsByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(mapService.getPointsByDevice(deviceId));
    }

    @GetMapping("/points/bounds")
    public ResponseEntity<List<RadiationPoint>> getPointsInBounds(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLon,
            @RequestParam Double maxLon) {
        return ResponseEntity.ok(mapService.getPointsInBounds(minLat, maxLat, minLon, maxLon));
    }

    @GetMapping("/points/level/{level}")
    public ResponseEntity<List<RadiationPoint>> getPointsByLevel(@PathVariable String level) {
        return ResponseEntity.ok(mapService.getPointsByLevel(level));
    }

    @DeleteMapping("/points/{pointId}")
    public ResponseEntity<Void> deletePoint(@PathVariable Long pointId) {
        mapService.deletePoint(pointId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/points/{pointId}")
    public ResponseEntity<RadiationPoint> updatePoint(
            @PathVariable Long pointId,
            @Valid @RequestBody RadiationPoint point) {
        return ResponseEntity.ok(mapService.updatePoint(pointId, point));
    }
} 