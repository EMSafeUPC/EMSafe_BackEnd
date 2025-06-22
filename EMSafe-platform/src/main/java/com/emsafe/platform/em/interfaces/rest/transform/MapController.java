package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import com.emsafe.platform.em.domain.services.map.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/map")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/points")
    public ResponseEntity<List<RadiationPoint>> getAllPoints() {
        try {
            List<RadiationPoint> points = mapService.getAllPoints();
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/points/bounds")
    public ResponseEntity<List<RadiationPoint>> getPointsInBounds(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLon,
            @RequestParam Double maxLon) {
        try {
            List<RadiationPoint> points = mapService.findPointsInBounds(minLat, maxLat, minLon, maxLon);
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/points")
    public ResponseEntity<Map<String, Object>> createPoint(@RequestBody RadiationPoint point) {
        Map<String, Object> response = new HashMap<>();
        try {
            RadiationPoint savedPoint = mapService.savePoint(point);
            response.put("success", true);
            response.put("message", "Punto creado exitosamente");
            response.put("data", savedPoint);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error al crear el punto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/points/{id}")
    public ResponseEntity<Map<String, Object>> updatePoint(
            @PathVariable Long id,
            @RequestBody RadiationPoint updatedPoint) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Verificar si el punto existe
            Optional<RadiationPoint> existingPoint = Optional.ofNullable(mapService.findById(id));
            
            if (existingPoint.isEmpty()) {
                response.put("success", false);
                response.put("message", "No se encontró el punto con ID: " + id);
                return ResponseEntity.notFound().build();
            }

            // Actualizar los campos del punto existente
            RadiationPoint pointToUpdate = existingPoint.get();
            pointToUpdate.setLatitude(updatedPoint.getLatitude());
            pointToUpdate.setLongitude(updatedPoint.getLongitude());
            pointToUpdate.setLevel(updatedPoint.getLevel());
            pointToUpdate.setColor(updatedPoint.getColor());
            pointToUpdate.setDescription(updatedPoint.getDescription());
            pointToUpdate.setRadiationValue(updatedPoint.getRadiationValue());
            pointToUpdate.setUnit(updatedPoint.getUnit());
            pointToUpdate.setDeviceId(updatedPoint.getDeviceId());

            // Guardar el punto actualizado
            RadiationPoint savedPoint = mapService.savePoint(pointToUpdate);

            response.put("success", true);
            response.put("message", "Punto actualizado exitosamente");
            response.put("data", savedPoint);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error al actualizar el punto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/points/{id}")
    public ResponseEntity<Map<String, Object>> deletePoint(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            mapService.deletePoint(id);
            response.put("success", true);
            response.put("message", "Punto eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error al eliminar el punto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/points/device/{deviceId}")
    public ResponseEntity<List<RadiationPoint>> getPointsByDevice(@PathVariable Long deviceId) {
        try {
            List<RadiationPoint> points = mapService.findPointsByDevice(deviceId);
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/points/level/{level}")
    public ResponseEntity<List<RadiationPoint>> getPointsByLevel(@PathVariable String level) {
        try {
            List<RadiationPoint> points = mapService.findPointsByLevel(level);
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getMapStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            List<RadiationPoint> allPoints = mapService.getAllPoints();
            
            stats.put("success", true);
            stats.put("data", Map.of(
                "totalPoints", allPoints.size(),
                "criticalPoints", allPoints.stream().filter(p -> "Crítico".equals(p.getLevel())).count(),
                "highPoints", allPoints.stream().filter(p -> "Alto".equals(p.getLevel())).count(),
                "mediumPoints", allPoints.stream().filter(p -> "Medio".equals(p.getLevel())).count(),
                "lowPoints", allPoints.stream().filter(p -> "Bajo".equals(p.getLevel())).count()
            ));
            
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error al obtener estadísticas: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/zones/nearby")
    public ResponseEntity<Map<String, Object>> getNearbyZones(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam Double radius) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", new HashMap<>()); // Implementar lógica de zonas cercanas si es necesario
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error al obtener zonas cercanas: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
