package org.karankumarr.timetracker.timelog.repository;

import org.karankumarr.timetracker.timelog.entity.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLogRepository extends JpaRepository<TimeLog, Integer> {
}
