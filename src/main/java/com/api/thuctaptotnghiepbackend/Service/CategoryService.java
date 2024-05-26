package com.api.thuctaptotnghiepbackend.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Category;

public interface CategoryService {
    Category createCategory(Category category);

    Category getCategoryById(Long categoryId);

    Page<Category> getAllCategories(Pageable pageable);

    Category updateCategory(Category category);

    void deleteCategory(Long categoryId);
}