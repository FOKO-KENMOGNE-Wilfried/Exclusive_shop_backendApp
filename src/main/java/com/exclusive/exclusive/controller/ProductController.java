package com.exclusive.exclusive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.exclusive.dao.request.AddProductOptionSizeRequest;
import com.exclusive.dao.response.AddProductOptionImageResponse;
import com.exclusive.dao.response.AddProductResponse;
import com.exclusive.dao.response.StringResponse;
import com.exclusive.exclusive.entity.Product;
import com.exclusive.exclusive.entity.ProductOptions;
import com.exclusive.exclusive.entity.ProductOptionsImages;
import com.exclusive.exclusive.entity.ProductOptionsSize;
import com.exclusive.exclusive.repository.ProductOptionRepository;
import com.exclusive.exclusive.service.IProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductService iProductService;
    private final ImageUploadController imageUploadController;
    private final ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductController(IProductService iProductService, ImageUploadController imageUploadController, ProductOptionRepository productOptionRepository) {
        this.iProductService = iProductService;
        this.imageUploadController = imageUploadController;
        this.productOptionRepository = productOptionRepository;
    }

    /**
     * Create a new product
     *
     * @param product the product to create
     * @return the ResponseEntity with status 200 (ok) and with body of the new
     *         product
     */
    @PostMapping("/addProduct")
    public ResponseEntity<AddProductResponse> saveProduct(@RequestBody Product product) {
        Product newProduct = iProductService.saveProduct(product);
        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setProduct(newProduct);
        addProductResponse.setMessage("Product create Successful !");
        return ResponseEntity.ok(addProductResponse);
    }

    /**
     * Add a new productOption
     *
     * @param productOption the productOption to create
     * @return the ResponseEntity with status 200 (ok) and with body of the new
     *         productOption
     */
    @PostMapping("/addProductOption")
    public ResponseEntity<String> addProductOption(@RequestBody ProductOptions productOptions) {
        iProductService.addOption(productOptions);
        return ResponseEntity.ok("Product Option create Successful");
    }

    /**
     * Add a new productOptionImage
     *
     * @param productOptionId the id of the productOption
     * @param file the image of the productOption to add
     * @return the ResponseEntity with status 200 (ok) and with body of the new
     *         productOptionImage
     */
    @PostMapping("/addProductOptionImage")
    public ResponseEntity<AddProductOptionImageResponse> addProductOptionImage(
            @RequestParam("productOptionId") Long productOptionId,
            @RequestParam("file") MultipartFile file) {

        try {
            // Get the prooductOption into the database
            ProductOptions productOption = productOptionRepository.findById(productOptionId).get();
            if (productOption == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new AddProductOptionImageResponse(null, "Product option not found"));
            }

            // uplaod the file
            String filePath = imageUploadController.uploadImage(file);

            // Create abd fill the productImage Object
            ProductOptionsImages productOptionsImages = new ProductOptionsImages();
            productOptionsImages.setProductOptions(productOption); // Lier l'entité
            productOptionsImages.setImageUrl(filePath);

            // Save imge option
            ProductOptionsImages newProductOptionsImages = iProductService.addOptionImages(productOptionsImages);

            // manage response
            AddProductOptionImageResponse response = new AddProductOptionImageResponse();
            response.setProductOptionsImages(newProductOptionsImages);
            response.setMessage("Product option image added successfully!");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AddProductOptionImageResponse(null, "Failed to add product option image"));
        }
    }

    /**
     * Add a new productOptionSize
     *
     * @param productOptionSize the productOptionSize to add
     * @return the ResponseEntity with status 200 (ok) and with body of the new
     *         productOptionSize
     */
    @PostMapping("/addProductOptionSize")
    public ResponseEntity<String> addProductOptionSize(@RequestBody AddProductOptionSizeRequest productOptionsSizeRequest) {

        // Get the prooductOption into the database
        System.out.println("----------------------------");
        System.out.println(productOptionsSizeRequest.getProductOptionsSize());
        ProductOptions productOption = productOptionRepository.findById(productOptionsSizeRequest.getProductOptionId()).get();
        if (productOption == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product option not found");
        }

        ProductOptionsSize productOptionsSize = new ProductOptionsSize();
        productOptionsSize.setProductOptions(productOption);
        productOptionsSize.setSize(productOptionsSizeRequest.getProductOptionsSize());

        iProductService.addOptionSize(productOptionsSize);

        return ResponseEntity.ok("Product Option create Successful");
    }

    /**
     * Get all products
     *
     * @return the ResponseEntity with status 200 (ok) and with body of the list of
     *         products
     */
    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return iProductService.getAllProducts();
    }

    /**
     * Get a product by ID.
     *
     * @param id the ID of the product to get
     * @return the ResponseEntity with status 200 (ok) and with body of the product,
     *         or woth status 404 (Not Found) if the product does not exist
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = iProductService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update a product by ID
     *
     * @param id the ID of the product to update
     * @return the ResponseEntity with status 200 (ok) and with body of the product
     *         updated product, or withe 404 (Not Found) if the product does not
     *         exist
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = iProductService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Delete a product by ID.
     *
     * @param id the ID of the product to delete
     * @return the ResponseEntity with status 200 (ok) and with body of the message
     *         "Product deleted successfully"
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<StringResponse> deleteProduct(@PathVariable Long id) {
        iProductService.deleteProduct(id);
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse("Product deleted successfully");
        return ResponseEntity.ok(stringResponse);
    }
}
