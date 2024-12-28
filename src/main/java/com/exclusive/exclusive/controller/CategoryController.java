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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exclusive.dao.response.StringResponse;
import com.exclusive.exclusive.entity.Category;
import com.exclusive.exclusive.service.ICategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

  private final ICategoryService iCategoryService;
  private final ImageUploadController imageUploadController;

  @Autowired
  public CategoryController(ICategoryService iCategoryService, ImageUploadController imageUploadController) {
    this.iCategoryService = iCategoryService;
    this.imageUploadController = imageUploadController;
  }

  /**
   * Create a new category
   *
   * @param category the category to create
   * @return the ResponseEntity with status 200 (ok) and with body of the new category
   */
  @PostMapping("/addCategory")
  public ResponseEntity<Category> addCategory(@RequestParam("name") String name, @RequestParam("image") MultipartFile file) {

    String filePath = imageUploadController.uploadImage(file);

    Category newCategory = new Category();
    newCategory.setName(name);
    newCategory.setImage(filePath);
    Category savedCategory = iCategoryService.addCategory(newCategory);
    return ResponseEntity.ok(savedCategory);
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
