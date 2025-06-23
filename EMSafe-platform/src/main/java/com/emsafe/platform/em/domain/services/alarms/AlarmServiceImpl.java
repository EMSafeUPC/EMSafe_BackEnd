package com.emsafe.platform.em.domain.services.alarms;

import com.emsafe.platform.em.domain.model.aggregates.alarms.Alarm;
import com.emsafe.platform.em.domain.repositories.AlarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository repo;

    public AlarmServiceImpl(AlarmRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Alarm> findAll() {
        return repo.findAll();
    }

    @Override
    public Alarm save(Alarm alarm) {
        return repo.save(alarm);
    }

    @Override
    public Alarm findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alarma no encontrada: " + id));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
