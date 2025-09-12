package org.karankumarr.timetracker.timelog.dto;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TimeLogRequest {
    private Long categoryId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
