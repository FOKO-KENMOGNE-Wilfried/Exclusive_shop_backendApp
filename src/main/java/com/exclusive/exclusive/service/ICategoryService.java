package com.exclusive.exclusive.service;

import java.util.List;

import com.exclusive.exclusive.entity.Category;

public interface ICategoryService {

  /**
   * Add a new category
   * 
   * @param category the category to save
   * @return the new category
   */
  public Category addCategory(Category category);

  /**
   * Get all categories
   * 
   * @return the list of categories
   */
  public List<Category> getAllCategories();

  /**
   * Update category
   * 
   * @param categoryId the category
   * @retun the updated category
   */
  public Category updateCategory(Long categoryId, Category updatedCategory);

  /**
   * Delete an category
   * 
   * @param categoryId the category to delete
   */
  public void deleteCategory(Long categoryId);

}
