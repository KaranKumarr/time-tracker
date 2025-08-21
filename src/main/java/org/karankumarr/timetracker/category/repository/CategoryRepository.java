package org.karankumarr.timetracker.category.repository;

import org.karankumarr.timetracker.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> { }
