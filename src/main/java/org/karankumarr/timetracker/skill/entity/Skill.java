package org.karankumarr.timetracker.skill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.karankumarr.timetracker.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int goalHours;
    private int loggedHours = 0;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
