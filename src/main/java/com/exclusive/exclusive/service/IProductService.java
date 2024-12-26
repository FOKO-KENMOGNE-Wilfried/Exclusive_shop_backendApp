package com.exclusive.exclusive.service;

import java.util.List;
import java.util.Optional;


import com.exclusive.exclusive.entity.Product;
import com.exclusive.exclusive.entity.ProductOptions;
import com.exclusive.exclusive.entity.ProductOptionsImages;
import com.exclusive.exclusive.entity.ProductOptionsSize;

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
     * Add ProductOption
     * 
     * @param productOption
     * @return the persisitent entity
     */
    public ProductOptions addOption(ProductOptions productOptions);

    /**
     * Add ProductOptionImage
     * 
     * @param productOptionImage
     * @return the persisitent entity
     */
    public ProductOptionsImages addOptionImages(ProductOptionsImages productOptionsImages);

    /**
     * Add ProductOptionSize
     * 
     * @param productOptionSize
     * @return the persisitent entity
     */
    public ProductOptionsSize addOptionSize(ProductOptionsSize productOptionsSize);

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
    public Product updateProduct(Long id, Product updatedProduct);

    /**
     * Delete the product by ID.
     * 
     * @param id the ID of the entity
     */
    public void deleteProduct(Long id);

    /**
     * Delete ProductOptionImage
     * 
     * @param productOptionImageId
     * @return the persisitent entity
     */
    public void deleteOptionImages(Long productOptionImageId);

    /**
     * Delete ProductOptionSize
     * 
     * @param productOptionSizeId
     * @return the persisitent entity
     */
    public void deleteOptionSize(Long productOptionSizeId);

    /**
     * Delete ProductOptionSize
     * 
     * @param productOptionId
     * @return the persisitent entity
     */
    public void deleteOption(Long productOptionId);

}
