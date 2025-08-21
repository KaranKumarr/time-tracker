package org.karankumarr.timetracker.category.controller;

import org.karankumarr.timetracker.category.dto.CategoryRequest;
import org.karankumarr.timetracker.category.dto.CategoryResponse;
import org.karankumarr.timetracker.category.service.CategoryService;
import org.karankumarr.timetracker.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategories() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Fetched successfully.",
                        this.categoryService.getCategories()
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(
                new ApiResponse<>(201, "Created.", this.categoryService.createCategory(categoryRequest)
                ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Updated.", this.categoryService.updateCategory(id, categoryRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>(204, "Deleted."));
    }

}
