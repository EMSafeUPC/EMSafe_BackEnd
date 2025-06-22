package com.emsafe.platform.em.application.devicesimpl;


import com.emsafe.platform.em.domain.model.aggregates.devices.MeasurementFrequency;
import com.emsafe.platform.em.domain.services.devices.MeasurementFrequencyService;
import com.emsafe.platform.em.infrastructure.persistance.jpa.devices.MeasurementFrequencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementFrequencyServiceImpl implements MeasurementFrequencyService {

    @Autowired
    private MeasurementFrequencyRepository frequencyRepository;

    @Override
    public List<MeasurementFrequency> GetAllFrequencies() {
        return frequencyRepository.findAll();
    }

    @Override
    public MeasurementFrequency GetFrequencyById(Long id) {
        return frequencyRepository.findById(id).orElse(null);
    }
}
