// src/main/java/com/emsafe/platform/em/domain/services/alarms/AlarmService.java
package com.emsafe.platform.em.domain.services.alarms;

import com.emsafe.platform.em.domain.model.aggregates.alarms.Alarm;

import java.util.List;

public interface AlarmService {
    List<Alarm> findAll();
    Alarm save(Alarm alarm);
    Alarm findById(Long id);
    void delete(Long id);
}
