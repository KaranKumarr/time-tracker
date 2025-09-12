package org.karankumarr.timetracker.category.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.karankumarr.timetracker.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 1000)
    private String description = "";
    private Integer goalHours = 0;
    private Integer loggedMinutes = 0;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime deadline;

    public GoalStatus status;
}
