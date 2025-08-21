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

import java.util.Optional;


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

    public TimeLogResponse createTimeLog(TimeLogRequest timeLogRequest) {

        if(timeLogRequest.getStartTime() == null || timeLogRequest.getEndTime() == null) {
            throw new IllegalArgumentException("Start and end time cannot be null");
        }
        if(timeLogRequest.getEndTime().isBefore(timeLogRequest.getStartTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        int userId = 2;
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id "+userId));

        TimeLog timeLogEntity = new TimeLog();
        if(timeLogRequest.getSkillId() != null) {
            Skill skill = skillRepository.findById(Math.toIntExact(timeLogRequest.getSkillId())).orElseThrow(() -> new IllegalArgumentException("Skill not found with id "+timeLogRequest.getSkillId()));
            timeLogEntity.setSkill(skill);
        }
        if(timeLogRequest.getDescription() != null) {
            timeLogEntity.setDescription(timeLogRequest.getDescription());
        }
        timeLogEntity.setStartTime(timeLogRequest.getStartTime());
        timeLogEntity.setEndTime(timeLogRequest.getEndTime());
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

    public TimeLogResponse updateTimeLog(Integer id, TimeLogRequest timeLogRequest) {

        TimeLog timeLogEntity =  timeLogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Time log not found with id "+id));

        if(timeLogRequest.getDescription() != null) {
            timeLogEntity.setDescription(timeLogRequest.getDescription());
        }
        if(timeLogRequest.getSkillId() != null) {
            Skill updatedSkill = skillRepository.findById(Math.toIntExact(timeLogRequest.getSkillId())).orElseThrow(() -> new IllegalArgumentException("Skill not found with id "+timeLogRequest.getSkillId()));
            timeLogEntity.setSkill(updatedSkill);
        }
        if(timeLogRequest.getStartTime() != null) {
            timeLogEntity.setStartTime(timeLogRequest.getStartTime());
        }
        if(timeLogRequest.getEndTime() != null) {
            timeLogEntity.setEndTime(timeLogRequest.getEndTime());
        }
        if(timeLogEntity.getEndTime().isBefore(timeLogEntity.getStartTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

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
