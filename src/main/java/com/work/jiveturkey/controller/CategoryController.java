package com.work.jiveturkey.controller;

import com.work.jiveturkey.model.Category;
import com.work.jiveturkey.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        LOGGER.info("Creating category: {}", category);
        ResponseEntity<?> response = new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
        LOGGER.info("Category created successfully");
        return response;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        LOGGER.info("Fetching all categories");
        ResponseEntity<?> response = new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
        LOGGER.info("Categories fetched successfully");
        return response;
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> editCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        LOGGER.info("Editing category with ID {}: {}", categoryId, category);
        categoryService.editCategory(category, categoryId);
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
        LOGGER.info("Category edited successfully");
        return response;
    }
}