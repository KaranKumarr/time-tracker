package org.karankumarr.timetracker.timelog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.karankumarr.timetracker.skill.entity.Skill;
import org.karankumarr.timetracker.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class TimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "skill_id", nullable = true)
    private Skill skill;

    private LocalDateTime startTime;  // e.g., 2025-08-20 18:30
    private LocalDateTime endTime;    // e.g., 2025-08-20 20:00

    private long durationMinutes;     // derived field for convenience

    private String description = "";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    @PreUpdate
    public void calculateDuration() {
        if (startTime != null && endTime != null) {
            this.durationMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
        }
    }

}
