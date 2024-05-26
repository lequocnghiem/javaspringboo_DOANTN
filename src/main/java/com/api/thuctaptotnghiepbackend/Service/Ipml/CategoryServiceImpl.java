package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Category;
import com.api.thuctaptotnghiepbackend.Repository.Category.CategoryRepository;
import com.api.thuctaptotnghiepbackend.Service.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.orElse(null);
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category updateCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(category.getId());
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            // Cập nhật tất cả các trường của Category
            updatedCategory.setName(category.getName());
            updatedCategory.setSlug(category.getSlug());
       
            updatedCategory.setParentId(category.getParentId());
            updatedCategory.setSortOrder(category.getSortOrder());
            updatedCategory.setMetakey(category.getMetakey());
            updatedCategory.setMetadesc(category.getMetadesc());
            updatedCategory.setCreatedAt(category.getCreatedAt());
            updatedCategory.setUpdatedAt(category.getUpdatedAt());
            updatedCategory.setCreatedBy(category.getCreatedBy());
            updatedCategory.setUpdatedBy(category.getUpdatedBy());
            updatedCategory.setStatus(category.getStatus());
            
            return categoryRepository.save(updatedCategory);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    
}
