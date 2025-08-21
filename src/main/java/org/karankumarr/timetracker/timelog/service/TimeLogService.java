package org.karankumarr.timetracker.timelog.service;

import org.karankumarr.timetracker.skill.entity.Skill;
import org.karankumarr.timetracker.skill.repository.SkillRepository;
import org.karankumarr.timetracker.timelog.dto.TimeLogRequest;
import org.karankumarr.timetracker.timelog.dto.TimeLogResponse;
import org.karankumarr.timetracker.timelog.entity.TimeLog;
import org.karankumarr.timetracker.timelog.repository.TimeLogRepository;
import org.karankumarr.timetracker.user.entity.User;
import org.karankumarr.timetracker.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class TimeLogService {


    private final TimeLogRepository timeLogRepository;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public TimeLogService(TimeLogRepository timeLogRepository, SkillRepository skillRepository, UserRepository userRepository) {
        this.timeLogRepository = timeLogRepository;
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public Page<TimeLogResponse> getTimeLogs(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        return this.timeLogRepository.findAll(pageable)
                .map(tl -> new TimeLogResponse(
                        tl.getId(),
                        tl.getSkill(),
                        tl.getStartTime(),
                        tl.getEndTime(),
                        tl.getDurationMinutes(),
                        tl.getDescription()
                ));
    }

    public TimeLogResponse createTimeLog(TimeLogRequest timeLog) {

        if(timeLog.getStartTime() == null || timeLog.getEndTime() == null) {
            throw new IllegalArgumentException("Start and end time cannot be null");
        }
        if(timeLog.getEndTime().isBefore(timeLog.getStartTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        int userId = 2;
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Skill not found with id "+userId));

        TimeLog timeLogEntity = new TimeLog();
        if(timeLog.getSkillId() != null) {
            Skill skill = skillRepository.findById(Math.toIntExact(timeLog.getSkillId())).orElseThrow(() -> new IllegalArgumentException("Skill not found with id "+timeLog.getSkillId()));
            timeLogEntity.setSkill(skill);
        }
        if(timeLog.getDescription() != null) {
            timeLogEntity.setDescription(timeLog.getDescription());
        }
        timeLogEntity.setStartTime(timeLog.getStartTime());
        timeLogEntity.setEndTime(timeLog.getEndTime());
        timeLogEntity.setUser(user);

        TimeLog timeLogSaved = this.timeLogRepository.save(timeLogEntity);

        return new TimeLogResponse(
                timeLogSaved.getId(),
                timeLogSaved.getSkill(),
                timeLogSaved.getStartTime(),
                timeLogSaved.getEndTime(),
                timeLogSaved.getDurationMinutes(),
                timeLogSaved.getDescription()
        );

    }


}
