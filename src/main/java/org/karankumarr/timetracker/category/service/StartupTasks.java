package org.karankumarr.timetracker.category.service;

import jakarta.transaction.Transactional;
import org.karankumarr.timetracker.category.repository.CategoryRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StartupTasks {

    private final CategoryRepository repo;

    public StartupTasks(CategoryRepository repo) {
        this.repo = repo;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void markExpiredOnStartup() {
        repo.markExpired(LocalDateTime.now());
    }
}
