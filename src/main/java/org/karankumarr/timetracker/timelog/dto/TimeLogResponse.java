package org.karankumarr.timetracker.timelog.dto;

import lombok.Getter;
import org.karankumarr.timetracker.category.entity.Category;

import java.time.LocalDateTime;

@Getter
public class TimeLogResponse {
    private final Long id;
    private final Category category;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long durationMinutes;
    private final String description;


    public TimeLogResponse(Long id, Category category, LocalDateTime startTime, LocalDateTime endTime, long durationMinutes, String description) {
        this.id = id;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.description = description;
    }
}