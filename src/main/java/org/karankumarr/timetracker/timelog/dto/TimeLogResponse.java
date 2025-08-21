package org.karankumarr.timetracker.timelog.dto;

import lombok.Getter;
import org.karankumarr.timetracker.skill.entity.Skill;

import java.time.LocalDateTime;

@Getter
public class TimeLogResponse {
    private final Long id;
    private final Skill skill;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long durationMinutes;
    private final String description;


    public TimeLogResponse(Long id, Skill skill, LocalDateTime startTime, LocalDateTime endTime, long durationMinutes, String description) {
        this.id = id;
        this.skill = skill;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.description = description;
    }
}