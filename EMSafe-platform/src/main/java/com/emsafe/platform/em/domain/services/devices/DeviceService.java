package com.emsafe.platform.em.domain.services.devices;

import com.emsafe.platform.em.domain.model.aggregates.devices.Device;

import java.util.List;

public interface DeviceService {

    List<Device> GetAllDevices();

    public abstract Device SaveOrUpdateDevice(Device device); // Guarda o actualiza un dispositivo

    public abstract void DeleteDevice(Long deviceId); // Elimina un dispositivo por ID
}
