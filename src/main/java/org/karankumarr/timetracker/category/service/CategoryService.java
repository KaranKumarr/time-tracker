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
        return categoryRepository.findAllWithLoggedMinutes();
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        if (categoryRequest.getName() == null) {
            throw new IllegalArgumentException("name is required");
        }
        if(categoryRequest.getStatus()==null) {
            throw new IllegalArgumentException("status is required");
        }

        Category categoryEntity = new Category();

        categoryEntity.setName(categoryRequest.getName());
        categoryEntity.setStatus(categoryRequest.getStatus());

        if (categoryRequest.getDescription() != null) {
            categoryEntity.setDescription(categoryRequest.getDescription());
        }

        if (categoryRequest.getGoalHours() != null) {
            categoryEntity.setGoalHours(categoryRequest.getGoalHours());
        }

        if (categoryRequest.getDeadline() != null) {
            categoryEntity.setDeadline(categoryRequest.getDeadline());
        }



        int userId = 2;
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));
        categoryEntity.setUser(user);

        Category categorySaved = categoryRepository.save(categoryEntity);

        return new CategoryResponse(
                categorySaved.getId(),
                categorySaved.getName(),
                categorySaved.getDescription(),
                categorySaved.getGoalHours(),
                categorySaved.getLoggedMinutes(),
                categorySaved.getCreatedAt(),
                categorySaved.getDeadline(),
                categorySaved.getStatus()
        );
    }

    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {

        Category categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found with id " + id));

        if (categoryRequest.getName() != null) {
            categoryEntity.setName(categoryRequest.getName());
        }
        if (categoryRequest.getDescription() != null) {
            categoryEntity.setDescription(categoryRequest.getDescription());
        }
        if (categoryRequest.getGoalHours() != null) {
            categoryEntity.setGoalHours(categoryRequest.getGoalHours());
        }
        if (categoryRequest.getLoggedMinutes() != null) {
            categoryEntity.setLoggedMinutes(categoryRequest.getLoggedMinutes());
        }
        if(categoryRequest.getDeadline() != null) {
            categoryEntity.setDeadline(categoryRequest.getDeadline());
        }
        if(categoryRequest.getStatus() != null) {
            categoryEntity.setStatus(categoryRequest.getStatus());
        }

        Category updatedCategory = categoryRepository.save(categoryEntity);

        return new CategoryResponse(
                updatedCategory.getId(),
                updatedCategory.getName(),
                updatedCategory.getDescription(),
                updatedCategory.getGoalHours(),
                updatedCategory.getLoggedMinutes(),
                updatedCategory.getCreatedAt(),
                updatedCategory.getDeadline(),
                updatedCategory.getStatus()
        );
    }

    public void deleteCategory(Integer id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found with id " + id));
        this.categoryRepository.delete(category);
    }
}
