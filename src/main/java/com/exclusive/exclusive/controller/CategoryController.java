package com.exclusive.exclusive.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exclusive.dao.response.StringResponse;
import com.exclusive.exclusive.entity.Category;
import com.exclusive.exclusive.service.ICategoryService;

@RestController
@RequestMapping("api/v1")
public class CategoryController {

  private ICategoryService iCategoryService;

  @Autowired
  public CategoryController(ICategoryService iCategoryService) {
    this.iCategoryService = iCategoryService;
  }

  /**
   * Create a new category
   *
   * @param category the category to create
   * @return the ResponseEntity with status 200 (ok) and with body of the new category
   */
  @PostMapping("/category")
  public ResponseEntity<Category> addCategory(@RequestBody Category category) {
    Category newCategory = iCategoryService.addCategory(category);
    return ResponseEntity.ok(newCategory);
  }

  /**
   * Get all categories
   * 
   * @return the ResponseEntity with status 200 (ok) and with body of the list of categories
   */
  @GetMapping("/categories")
  public List<Category> getAllCategories() {
    return iCategoryService.getAllCategories();
  }

  /**
   * Update a category
   * 
   * @param category the category to update
   * @return the ResponseEntity with status 200 (ok) and with body of the new category
   */
  @PutMapping("/category/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    Category updatedCategory = iCategoryService.updateCategory(id, category);
    return ResponseEntity.ok(updatedCategory);
  }

  /**
   * Delete a category
   * 
   * @param id
   * @return the ResponseEntity with the status 200 (ok) and with body of the message "category deleted successfully"
   */
  @DeleteMapping("/category/{id}")
  public ResponseEntity<StringResponse> deleteCategory(@PathVariable Long id) {
    iCategoryService.deleteCategory(id);
    StringResponse stringResponse = new StringResponse();
    stringResponse.setResponse("Category deleted");
    return ResponseEntity.ok(stringResponse);
  }

}
