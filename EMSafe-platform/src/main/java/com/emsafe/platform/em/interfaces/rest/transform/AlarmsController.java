// src/main/java/com/emsafe/platform/em/interfaces/rest/transform/AlarmsController.java
package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.alarms.Alarm;
import com.emsafe.platform.em.domain.services.alarms.AlarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/alarms", produces = "application/json")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.OPTIONS }
)
@Tag(name = "Alarm", description = "Endpoints de Alarmas")
public class AlarmsController {

    private final AlarmService alarmService;

    public AlarmsController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las alarmas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alarmas encontradas")
    })
    public ResponseEntity<List<Alarm>> getAll() {
        return ResponseEntity.ok(alarmService.findAll());
    }

    @PostMapping
    @Operation(summary = "Crear nueva alarma")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Alarma creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Alarm> create(@Valid @RequestBody Alarm alarm) {
        Alarm saved = alarmService.save(alarm);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una alarma existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alarma actualizada"),
            @ApiResponse(responseCode = "404", description = "Alarma no encontrada")
    })
    public ResponseEntity<Alarm> update(
            @PathVariable Long id,
            @Valid @RequestBody Alarm alarm) {
        alarm.setId(id);
        Alarm updated = alarmService.save(alarm);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una alarma")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Alarma eliminada"),
            @ApiResponse(responseCode = "404", description = "Alarma no encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alarmService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
