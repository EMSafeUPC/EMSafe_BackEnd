package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.devices.MeasurementFrequency;
import com.emsafe.platform.em.domain.model.aggregates.devices.Status;
import com.emsafe.platform.em.domain.model.aggregates.devices.Type;
import com.emsafe.platform.em.domain.services.devices.MeasurementFrequencyService;
import com.emsafe.platform.em.domain.services.devices.StatusService;
import com.emsafe.platform.em.domain.services.devices.TypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/device-catalogs", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.OPTIONS }
)
@Tag(name = "Device Catalogs", description = "Static lists for device types, statuses and frequencies")
public class DeviceCatalogController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private MeasurementFrequencyService frequencyService;

    @GetMapping("/statuses")
    public List<Status> getStatuses() {
        return statusService.GetAllStatuses();
    }

    @GetMapping("/types")
    public List<Type> getTypes() {
        return typeService.GetAllTypes();
    }

    @GetMapping("/frequencies")
    public List<MeasurementFrequency> getFrequencies() {
        return frequencyService.GetAllFrequencies();
    }
}
