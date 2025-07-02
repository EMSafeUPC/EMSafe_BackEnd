package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.devices.Device;
import com.emsafe.platform.em.domain.services.devices.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/v1/devices", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(
        origins = { "https://emsafe.netlify.app", "http://localhost:4200" },
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.OPTIONS }
)
@Tag(name = "Device", description = "Available Device Endpoints")
public class DevicesController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    @Operation(summary = "Get all devices", description = "Get all devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices found"),
            @ApiResponse(responseCode = "404", description = "Devices not found")
    })
    @ResponseBody
    public ResponseEntity<List<Device>> GetAll() {
        List<Device> list = deviceService.GetAllDevices();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @Operation(summary = "Create a new device", description = "Create a new device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> CreateDevice(@RequestBody Device device) {
        Map<String, Object> exit = new HashMap<>();
        try {

            Device savedDevice = deviceService.SaveOrUpdateDevice(device);

            if (savedDevice == null) {
                exit.put("message", "Register Incorrect");
            } else {
                exit.put("message", "Register Correct");
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit.put("message", "Register Incorrect");
        }
        return ResponseEntity.ok(exit);
    }

    @PutMapping("/{deviceId}")
    @Operation(summary = "Update a device", description = "Update a device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> UpdateDevice(@RequestBody Device device) {
        Map<String, Object> exit = new HashMap<>();
        try {
            Device updatedDevice = deviceService.SaveOrUpdateDevice(device);
            if (updatedDevice == null) {
                exit.put("message", "Update Incorrect");
            } else {
                exit.put("message", "Update Correct");
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit.put("message", "Update Incorrect");
        }
        return ResponseEntity.ok(exit);
    }

    @DeleteMapping("/{deviceId}")
    @Operation(summary = "Delete a device", description = "Delete a device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> DeleteDevice(@PathVariable("deviceId") long id) {
        Map<String, Object> exit = new HashMap<>();
        try {
            deviceService.DeleteDevice(id);
            exit.put("message", "Delete Correct");
        } catch (Exception e) {
            e.printStackTrace();
            exit.put("message", "Delete Incorrect");
        }
        return ResponseEntity.ok(exit);
    }
}
