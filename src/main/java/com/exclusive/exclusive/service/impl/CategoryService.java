package com.exclusive.exclusive.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exclusive.exclusive.entity.Category;
import com.exclusive.exclusive.repository.CategoryRepository;
import com.exclusive.exclusive.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category addCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category updateCategory(Long categoryId, Category updatedCategory) {
    Optional<Category> existingProduct = categoryRepository.findById(categoryId);
    if (existingProduct.isPresent()) {
      Category category = existingProduct.get();
      category.setImage(updatedCategory.getImage());
      category.setName(updatedCategory.getName());
      return categoryRepository.save(category);
    } else {
      throw new RuntimeException("Category not found");
    }
  }

  @Override
  public void deleteCategory(Long categoryId) {
    categoryRepository.deleteById(categoryId);
  }
  
}
