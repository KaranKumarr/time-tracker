package org.karankumarr.timetracker.category.dto;

import lombok.Getter;

@Getter
public class CategoryRequest {
    private String name;
    private String description;
    private Integer goalHours;
    private final Integer loggedHours = 0;
}
