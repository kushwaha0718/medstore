package com.nitish.medstore.repository;


import com.nitish.medstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByCategoryNameIgnoreCase(String categoryName);
}
