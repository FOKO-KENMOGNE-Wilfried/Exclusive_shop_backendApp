package com.exclusive.exclusive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exclusive.exclusive.entity.Product;
import com.exclusive.exclusive.repository.ProductRepository;

/**
 * Service class for managing Product entities.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Save a produuct
     * 
     * @param product the entity to save
     * @return the persisted entity
     */
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * Get all products.
     * 
     * @return the list od entities
     */
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Get one product by ID
     * 
     * @param id the ID of the entity
     * @return the entity
     */
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    /**
     * Update a product
     * 
     * @param id the ID of the entity
     * @param updatedProduct the updated entity
     * @return the updates entity
     */
    public Product updateProduct(long id, Product updatedProduct){
        Optional<Product> existingProduct = productRepository.findById(id);
        if(existingProduct.isPresent()){
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    /**
     * Delete the product by ID.
     * 
     * @param id the ID of the entity
     */
    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

}
