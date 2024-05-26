package com.api.thuctaptotnghiepbackend.Controller.Product;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.Productoption;
import com.api.thuctaptotnghiepbackend.Service.ProductoptionSevice;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("api/productoption")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductoptionController {
    

    private ProductoptionSevice productoptionSevice;

    // Create Product REST API
    @PostMapping
    public ResponseEntity<Productoption> createProduct(@RequestBody Productoption productoption) {
        // Product.setPhoto(Product.getPhoto() + ".png");
         productoption.setCreatedAt(LocalDateTime.now());
        Productoption savedProduct = productoptionSevice.createOption(productoption);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

   
    // Get Product by id REST API
    // http://localhost:8080/api/Products/1
    @GetMapping("{id}")
    public ResponseEntity<Productoption> getProductById(@PathVariable("id") String ProductId) {
        Productoption productoption = productoptionSevice.getProductById(ProductId);
        return new ResponseEntity<>(productoption, HttpStatus.OK);
    }

    // Get All Products REST API
    // http://localhost:8080/api/Products

    @GetMapping
    public ResponseEntity<Page<Productoption>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1000") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Productoption> productPage = productoptionSevice.getAllProducts(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    // Update Product REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/Products/1
    public ResponseEntity<Productoption> updateProduct(@PathVariable("id") String ProductId,
            @RequestBody Productoption productoption) {
        productoption.setId(ProductId);
        
        Productoption updatedProduct = productoptionSevice.updateProduct(productoption);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String ProductId) {
        productoptionSevice.deleteProduct(ProductId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }
}
