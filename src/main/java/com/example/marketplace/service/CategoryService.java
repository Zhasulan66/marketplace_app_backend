package com.example.marketplace.service;

import com.example.marketplace.entity.Category;
import com.example.marketplace.exception.CategoryNotFoundException;
import com.example.marketplace.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        // Check if the category with the given ID exists
        if (!categoryRepo.existsById(id)) {
            throw new CategoryNotFoundException("Category with ID " + id + " not found.");
        }

        updatedCategory.setId(id); // Set the ID of the updated category
        return categoryRepo.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
