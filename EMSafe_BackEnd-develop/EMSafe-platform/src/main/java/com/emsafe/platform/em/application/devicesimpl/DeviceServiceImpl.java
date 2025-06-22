package com.emsafe.platform.em.application.devicesimpl;

import com.emsafe.platform.em.domain.model.aggregates.devices.Device;
import com.emsafe.platform.em.domain.services.devices.DeviceService;
import com.emsafe.platform.em.infrastructure.persistance.jpa.devices.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> GetAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device SaveOrUpdateDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void DeleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
