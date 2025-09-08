package org.karankumarr.timetracker.category.repository;

import org.karankumarr.timetracker.category.dto.CategoryResponse;
import org.karankumarr.timetracker.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT new org.karankumarr.timetracker.category.dto.CategoryResponse(" +
            "c.id, c.name, c.description, c.goalHours, " +
            "CAST(COALESCE(SUM(t.durationMinutes), 0) AS integer), " +
            "c.createdAt, c.deadline, c.status) " +
            "FROM Category c " +
            "LEFT JOIN TimeLog t ON t.category.id = c.id " +
            "GROUP BY c.id, c.name, c.description, c.goalHours, c.createdAt, c.deadline, c.status")
    List<CategoryResponse> findAllWithLoggedMinutes();


}
