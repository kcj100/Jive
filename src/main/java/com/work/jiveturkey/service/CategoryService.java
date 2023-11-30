package com.work.jiveturkey.service;

import com.work.jiveturkey.model.Category;
import com.work.jiveturkey.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void editCategory(Category category, Long categoryId) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if(existingCategory.isPresent()) {
            existingCategory.get().setCategoryName(category.getCategoryName());
        } else {
            throw new NoSuchElementException("Category with id " + categoryId + " not found.");
        }
    }
}
