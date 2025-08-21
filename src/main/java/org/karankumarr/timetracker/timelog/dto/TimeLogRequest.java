package org.karankumarr.timetracker.timelog.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TimeLogRequest {
    private Long skillId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
