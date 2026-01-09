package com.nitish.medstore.service;

import com.nitish.medstore.entity.Category;
import com.nitish.medstore.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public String addCategory(String categoryName) {
        String name = categoryName.trim();

        if (categoryRepository.existsByCategoryNameIgnoreCase(name)) {
            return "Category already exists";
        }

        Category category = new Category();
        category.setCategoryName(name);

        categoryRepository.save(category);
        return "Success";
    }

    @Transactional
    public String deleteCategory(Integer categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            return "Category not found";
        }

        categoryRepository.deleteById(categoryId);
        return "Category deleted successfully";
    }

    @Transactional
    public String updateCategory(Integer categoryId, String newName) {
        Category category = categoryRepository.findById(categoryId)
                .orElse(null);

        if (category == null) {
            return "Category not found";
        }
        String oldName = category.getCategoryName();

        // If name already exists in category table
        if (categoryRepository.existsByCategoryNameIgnoreCase(newName)) {
            return "Category name already exists";
        }

        // Update category table name
        category.setCategoryName(newName);
        categoryRepository.save(category);

        return "Category updated successfully";
    }

}
