package com.exclusive.exclusive.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exclusive.exclusive.entity.Product;
import com.exclusive.exclusive.entity.ProductOptions;
import com.exclusive.exclusive.entity.ProductOptionsImages;
import com.exclusive.exclusive.entity.ProductOptionsSize;
import com.exclusive.exclusive.repository.ProductOptionImageRepository;
import com.exclusive.exclusive.repository.ProductOptionRepository;
import com.exclusive.exclusive.repository.ProductOptionSizeRepository;
import com.exclusive.exclusive.repository.ProductRepository;
import com.exclusive.exclusive.service.IProductService;

/**
 * Service class for managing Product entities.
 */
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionImageRepository productOptionImageRepository;
    private final ProductOptionSizeRepository productOptionSizeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductOptionRepository productOptionRepository,
            ProductOptionImageRepository productOptionImageRepository,
            ProductOptionSizeRepository productOptionSizeRepository) {
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
        this.productOptionImageRepository = productOptionImageRepository;
        this.productOptionSizeRepository = productOptionSizeRepository;
    }

    /**
     * Save a produuct
     * 
     * @param product the entity to save
     * @return the persisted entity
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Get all products.
     * 
     * @return the list od entities
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get one product by ID
     * 
     * @param id the ID of the entity
     * @return the entity
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Update a product
     * 
     * @param id             the ID of the entity
     * @param updatedProduct the updated entity
     * @return the updates entity
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
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
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductOptions addOption(ProductOptions productOptions) {
        return productOptionRepository.save(productOptions);
    }

    @Override
    public ProductOptionsImages addOptionImages(ProductOptionsImages productOptionsImages) {
        return productOptionImageRepository.save(productOptionsImages);
    }

    @Override
    public ProductOptionsSize addOptionSize(ProductOptionsSize productOptionsSize) {
        return productOptionSizeRepository.save(productOptionsSize);
    }

    @Override
    public void deleteOptionImages(Long productOptionImageId) {
        productOptionImageRepository.deleteById(productOptionImageId);
    }

    @Override
    public void deleteOptionSize(Long productOptionSizeId) {
        productOptionSizeRepository.deleteById(productOptionSizeId);
    }

    @Override
    public void deleteOption(Long productOptionId) {
        productOptionRepository.deleteById(productOptionId);
    }

}
