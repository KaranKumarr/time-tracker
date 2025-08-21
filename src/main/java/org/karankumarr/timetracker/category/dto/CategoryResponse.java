package org.karankumarr.timetracker.category.dto;

import lombok.Getter;
import org.karankumarr.timetracker.category.entity.Category;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final int goalHours;
    private final int loggedHours;
    private final LocalDateTime createdAt;


    public CategoryResponse(Long id, String name, String description, int goalHours, int loggedHours, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goalHours = goalHours;
        this.loggedHours = loggedHours;
        this.createdAt = createdAt;
    }
}