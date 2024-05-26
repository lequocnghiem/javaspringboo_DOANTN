package com.api.thuctaptotnghiepbackend.Controller.Product;

import java.time.LocalDateTime;
import java.util.List;

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

import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionvalueRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductService;
import com.api.thuctaptotnghiepbackend.Service.ProductoptionvalueService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/productoptionvalue")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductoptionvalueController {
    
    private ProductoptionvalueService productoptionvalueService;
    private  ProductoptionvalueRepository ProductoptionvalueRepository;
    private ProductService productService;
    // Create Product REST API
    @PostMapping
    public ResponseEntity<Productoptionvalue> createProduct(@RequestBody Productoptionvalue productoption) {
        // Product.setPhoto(Product.getPhoto() + ".png");
          productoption.setCreatedAt(LocalDateTime.now());
        Productoptionvalue savedProduct = productoptionvalueService.createOption(productoption);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

   
    // Get Product by id REST API
    // http://localhost:8080/api/Products/1
    @GetMapping("{id}")
    public ResponseEntity<Productoptionvalue> getProductById(@PathVariable("id") Long ProductId) {
        Productoptionvalue productoption = productoptionvalueService.getProductById(ProductId);
        return new ResponseEntity<>(productoption, HttpStatus.OK);
    }

    // Get All Products REST API
    // http://localhost:8080/api/Products

    @GetMapping
    public ResponseEntity<Page<Productoptionvalue>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1000") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Productoptionvalue> productPage = productoptionvalueService.getAllProducts(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    // Update Product REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/Products/1
    public ResponseEntity<Productoptionvalue> updateProduct(@PathVariable("id") Long ProductId,
            @RequestBody Productoptionvalue productoption) {
        productoption.setId(ProductId);
       
        Productoptionvalue updatedProduct = productoptionvalueService.updateProduct(productoption);

        
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long ProductId) {
        productoptionvalueService.deleteProduct(ProductId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }




 
    



    // else if (price != null) {
    //     // Tìm kiếm theo giá
    //     products = ProductoptionvalueRepository.findByProduct_priceBetween(price - 10, price + 10);
    // } else if (value != null) {
    //     // Tìm kiếm theo giá trị
    //     products = productoptionvalueService.findProductsByValue(value);
    // } else {

}
