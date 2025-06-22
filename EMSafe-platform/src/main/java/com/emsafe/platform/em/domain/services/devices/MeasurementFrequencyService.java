package com.emsafe.platform.em.domain.services.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.MeasurementFrequency;

import java.util.List;

public interface MeasurementFrequencyService {

    List<MeasurementFrequency> GetAllFrequencies();

    public abstract MeasurementFrequency GetFrequencyById(Long id); // Devuelve una frecuencia específica
}
