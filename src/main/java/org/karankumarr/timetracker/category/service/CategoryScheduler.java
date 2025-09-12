package org.karankumarr.timetracker.category.service;

import jakarta.transaction.Transactional;
import org.karankumarr.timetracker.category.repository.CategoryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryScheduler {

    private final CategoryRepository repo;

    public CategoryScheduler(CategoryRepository repo) {
        this.repo = repo;
    }

    @Scheduled(cron = "0 0 */12 * * *")   // at minute 0, second 0, every 12th hour
    @Transactional
    public void expireCategories() {
        repo.markExpired(LocalDateTime.now());
    }
}
