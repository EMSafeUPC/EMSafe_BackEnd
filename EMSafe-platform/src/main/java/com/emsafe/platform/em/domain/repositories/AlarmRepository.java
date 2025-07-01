package com.emsafe.platform.em.domain.repositories;

import com.emsafe.platform.em.domain.model.aggregates.alarms.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {}
