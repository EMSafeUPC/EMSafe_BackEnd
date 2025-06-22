package com.emsafe.platform.em.application.devicesimpl;

import com.emsafe.platform.em.domain.model.aggregates.devices.Status;
import com.emsafe.platform.em.domain.services.devices.StatusService;
import com.emsafe.platform.em.infrastructure.persistance.jpa.devices.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> GetAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status GetStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }
}
