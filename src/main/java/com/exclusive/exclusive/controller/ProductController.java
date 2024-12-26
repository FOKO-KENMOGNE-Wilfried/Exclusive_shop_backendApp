package com.exclusive.exclusive.controller;

import java.util.List;
import java.util.Optional;

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

import com.exclusive.dao.response.AddProductResponse;
import com.exclusive.dao.response.StringResponse;
import com.exclusive.exclusive.entity.Product;
import com.exclusive.exclusive.service.IProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final IProductService iProductService;

    @Autowired
    public ProductController(IProductService iProductService){
        this.iProductService = iProductService;
    }

    /**
     * Create a new product
     *
     * @param product the product to create
     * @return the ResponseEntity with status 200 (ok) and with body of the new product
     */
    @PostMapping("/product")
    public ResponseEntity<AddProductResponse> saveProduct(@RequestBody Product product){
        Product newProduct = iProductService.saveProduct(product);
        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setProduct(newProduct);
        addProductResponse.setMessage("Product create Successful !");
        return ResponseEntity.ok(addProductResponse);
    }

    /**
     * Get all products
     *
     * @return the ResponseEntity with status 200 (ok) and with body of the list of products
     */
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return iProductService.getAllProducts();
    }

    /**
     * Get a product by ID.
     *
     * @param id the ID of the product to get
     * @return the ResponseEntity with status 200 (ok) and with body of the product, 
     * or woth status 404 (Not Found) if the product does not exist
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = iProductService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update a product by ID
     *
     * @param id the ID of the product to update
     * @return the ResponseEntity with status 200 (ok) and with body of the product
     * updated product, or withe 404 (Not Found) if the product does not exist
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updatedProduct = iProductService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Delete a product by ID.
     *
     * @param id the ID of the product to delete
     * @return the ResponseEntity with status 200 (ok)
     * and with body of the message "Product deleted successfully"
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<StringResponse> deleteProduct(@PathVariable Long id){
        iProductService.deleteProduct(id);
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse("Product deleted successfully");
        return ResponseEntity.ok(stringResponse);
    }
}
