package org.karankumarr.timetracker.category.entity;

public enum GoalStatus {
    ACTIVE,        // currently in progress
    COMPLETED,     // finished successfully
    ARCHIVED,      // hidden but not deleted
    EXPIRED;       // deadline passed without completion
}
