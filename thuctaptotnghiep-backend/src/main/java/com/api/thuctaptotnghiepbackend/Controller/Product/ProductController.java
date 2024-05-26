package com.api.thuctaptotnghiepbackend.Controller.Product;




import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Service.ProductService;


@RestController
@AllArgsConstructor
@RequestMapping("api/product")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductController {

    private ProductService productService;

    // Create Product REST API
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product Product) {
         Product.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productService.createProduct(Product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

   
    // Get Product by id REST API
    // http://localhost:8080/api/Products/1
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String ProductId) {
        Product Product = productService.getProductById(ProductId);
        return new ResponseEntity<>(Product, HttpStatus.OK);
    }

    // Get All Products REST API
    // http://localhost:8080/api/Products

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllProducts(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    // Update Product REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/Products/1
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String ProductId,
            @RequestBody Product Product) {
        Product.setId(ProductId);
         Product.setUpdatedAt(LocalDateTime.now());
        Product updatedProduct = productService.updateProduct(Product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String ProductId) {
        productService.deleteProduct(ProductId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }
}

