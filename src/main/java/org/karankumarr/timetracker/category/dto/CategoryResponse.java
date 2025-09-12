package org.karankumarr.timetracker.category.dto;

import lombok.Getter;
import org.karankumarr.timetracker.category.entity.Category;
import org.karankumarr.timetracker.category.entity.GoalStatus;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Integer goalHours;
    private final Integer loggedMinutes;
    private final LocalDateTime createdAt;
    private final LocalDateTime deadline;
    private final GoalStatus status;


    public CategoryResponse(Long id, String name, String description, Integer goalHours, Integer loggedMinutes, LocalDateTime createdAt, LocalDateTime deadline, GoalStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goalHours = goalHours;
        this.loggedMinutes = loggedMinutes;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.status = status;
    }
}