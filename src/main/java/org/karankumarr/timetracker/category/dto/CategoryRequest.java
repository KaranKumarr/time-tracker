package org.karankumarr.timetracker.category.dto;

import lombok.Getter;
import org.karankumarr.timetracker.category.entity.GoalStatus;

import java.time.LocalDateTime;

@Getter
public class CategoryRequest {
    private String name;
    private String description;
    private Integer goalHours;
    private final Integer loggedHours = 0;
    private final GoalStatus status = GoalStatus.ACTIVE;
    public LocalDateTime deadline;
}
