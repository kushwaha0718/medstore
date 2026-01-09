package com.nitish.medstore.controller;

import com.nitish.medstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getAll());
    }

    @PostMapping("/add/{categoryName}")
    public ResponseEntity<?> addCategory(@PathVariable String categoryName){
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", categoryService.addCategory(categoryName)));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(
                Map.of("message", categoryService.deleteCategory(categoryId))
        );
    }

    @PutMapping("/update-category/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Integer categoryId,
            @RequestParam String newCategoryName
    ) {
        return ResponseEntity.ok(
                Map.of("message", categoryService.updateCategory(categoryId, newCategoryName.trim()))
        );
    }
}
