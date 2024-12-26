package com.exclusive.exclusive.service;

import java.util.List;
import java.util.Optional;


import com.exclusive.exclusive.entity.Product;

/**
 * Service interface for managing Product entities.
 */
public interface IProductService {

    /**
     * Save a produuct
     * 
     * @param product the entity to save
     * @return the persisted entity
     */
    public Product saveProduct(Product product);

    /**
     * Get all products.
     * 
     * @return the list od entities
     */
    public List<Product> getAllProducts();

    /**
     * Get one product by ID
     * 
     * @param id the ID of the entity
     * @return the entity
     */
    public Optional<Product> getProductById(Long id);

    /**
     * Update a product
     * 
     * @param id the ID of the entity
     * @param updatedProduct the updated entity
     * @return the updates entity
     */
    public Product updateProduct(long id, Product updatedProduct);

    /**
     * Delete the product by ID.
     * 
     * @param id the ID of the entity
     */
    public void deleteProduct(long id);

}
