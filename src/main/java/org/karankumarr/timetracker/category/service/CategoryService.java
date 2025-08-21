package org.karankumarr.timetracker.category.service;

import org.karankumarr.timetracker.category.dto.CategoryRequest;
import org.karankumarr.timetracker.category.dto.CategoryResponse;
import org.karankumarr.timetracker.category.entity.Category;
import org.karankumarr.timetracker.category.repository.CategoryRepository;
import org.karankumarr.timetracker.user.entity.User;
import org.karankumarr.timetracker.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream().map((cat -> new CategoryResponse(
                cat.getId(),
                cat.getName(),
                cat.getDescription(),
                cat.getGoalHours(),
                cat.getLoggedHours(),
                cat.getCreatedAt()
        ))).toList();
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        if(categoryRequest.getName() == null){
            throw new IllegalArgumentException("name is required");
        }

        Category categoryEntity = new Category();

        categoryEntity.setName(categoryRequest.getName());

        if(categoryRequest.getDescription() != null){
            categoryEntity.setDescription(categoryRequest.getDescription());
        }

        if(categoryRequest.getGoalHours() != null){
            categoryEntity.setGoalHours(categoryRequest.getGoalHours());
        }

        int userId = 2;
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id "+userId));
        categoryEntity.setUser(user);

        Category categorySaved =  categoryRepository.save(categoryEntity);

        return new CategoryResponse(
                categorySaved.getId(),
                categorySaved.getName(),
                categorySaved.getDescription(),
                categorySaved.getGoalHours(),
                categorySaved.getLoggedHours(),
                categorySaved.getCreatedAt()
        );
    }

}
