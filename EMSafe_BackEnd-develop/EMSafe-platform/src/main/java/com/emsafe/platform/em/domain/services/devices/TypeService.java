package com.emsafe.platform.em.domain.services.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.Type;

import java.util.List;

public interface TypeService {

    List<Type> GetAllTypes();

    public abstract Type GetTypeById(Long id); // Devuelve un tipo específico
}
