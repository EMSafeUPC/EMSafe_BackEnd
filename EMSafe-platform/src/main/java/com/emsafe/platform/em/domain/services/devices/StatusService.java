package com.emsafe.platform.em.domain.services.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.Status;

import java.util.List;

public interface StatusService {

    List<Status> GetAllStatuses();

    public abstract Status GetStatusById(Long id); // Devuelve un status específico
}
