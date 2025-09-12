package org.karankumarr.timetracker.category.repository;

import jakarta.transaction.Transactional;
import org.karankumarr.timetracker.category.dto.CategoryResponse;
import org.karankumarr.timetracker.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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



    @Modifying
    @Transactional
    @Query("""
    UPDATE Category c
       SET c.status = org.karankumarr.timetracker.category.entity.GoalStatus.EXPIRED
     WHERE c.deadline < :now
       AND c.status = org.karankumarr.timetracker.category.entity.GoalStatus.ACTIVE
""")
    void markExpired(@Param("now") LocalDateTime now);

}
