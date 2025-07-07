package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.map.RadiationPoint;
import com.emsafe.platform.em.domain.services.map.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/map", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Radiation Map", description = "Endpoints para gestión de puntos de radiación")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/points")
    @Operation(summary = "Listar todos los puntos de radiación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Puntos encontrados"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    public ResponseEntity<List<RadiationPoint>> getAllPoints() {
        return ResponseEntity.ok(mapService.getAllPoints());
    }

    @PostMapping("/points")
    @Operation(summary = "Crear nuevo punto de radiación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Punto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el punto")
    })
    public ResponseEntity<Map<String, Object>> createPoint(@RequestBody RadiationPoint point) {
        Map<String, Object> response = new HashMap<>();
        try {
            RadiationPoint saved = mapService.savePoint(point);
            response.put("success", true);
            response.put("message", "Punto creado exitosamente");
            response.put("data", saved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al crear el punto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/points/{id}")
    @Operation(summary = "Actualizar punto de radiación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Punto actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Punto no encontrado")
    })
    public ResponseEntity<Map<String, Object>> updatePoint(@PathVariable Long id, @RequestBody RadiationPoint updatedPoint) {
        Map<String, Object> response = new HashMap<>();
        Optional<RadiationPoint> existing = Optional.ofNullable(mapService.findById(id));

        if (existing.isEmpty()) {
            response.put("message", "No se encontró el punto con ID: " + id);
            return ResponseEntity.notFound().build();
        }

        RadiationPoint point = existing.get();
        point.setLatitude(updatedPoint.getLatitude());
        point.setLongitude(updatedPoint.getLongitude());
        point.setLevel(updatedPoint.getLevel());
        point.setColor(updatedPoint.getColor());
        point.setDescription(updatedPoint.getDescription());
        point.setRadiationValue(updatedPoint.getRadiationValue());
        point.setUnit(updatedPoint.getUnit());
        point.setDeviceId(updatedPoint.getDeviceId());

        RadiationPoint saved = mapService.savePoint(point);
        response.put("message", "Punto actualizado correctamente");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/points/{id}")
    @Operation(summary = "Eliminar punto de radiación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Punto eliminado"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar punto")
    })
    public ResponseEntity<Map<String, Object>> deletePoint(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            mapService.deletePoint(id);
            response.put("message", "Punto eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al eliminar: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
