package com.emsafe.platform.em.application.devicesimpl;

import com.emsafe.platform.em.domain.model.aggregates.devices.Type;
import com.emsafe.platform.em.domain.services.devices.TypeService;
import com.emsafe.platform.em.infrastructure.persistance.jpa.devices.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> GetAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type GetTypeById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }
}
