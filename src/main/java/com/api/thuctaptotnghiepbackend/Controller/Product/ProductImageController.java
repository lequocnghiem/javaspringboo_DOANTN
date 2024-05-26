package com.api.thuctaptotnghiepbackend.Controller.Product;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.Productimage;
import com.api.thuctaptotnghiepbackend.Service.ProducimageService;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("api/productimage")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductImageController {
     private ProducimageService producimageService;

     @PostMapping
     public ResponseEntity<List<Productimage>> uploadImages(@RequestBody List<Productimage> productImages) {
       

        for (Productimage productImage : productImages) {
            productImage.setId(null);
        }
             List<Productimage> savedProducts = producimageService.createProductImages(productImages);
             return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
       
            
         
     }
     
     


@GetMapping("{id}")
    public ResponseEntity<Productimage> getProductById(@PathVariable("id") Long ProductId) {
        Productimage productimage = producimageService.getProductByIdimage(ProductId);
        return new ResponseEntity<>(productimage, HttpStatus.OK);
    }


 @GetMapping
    public ResponseEntity<Page<Productimage>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1000") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Productimage> productPage = producimageService.getAllProductsimage(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    // Update Product REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/Products/1
    public ResponseEntity<Productimage> updateProduct(@PathVariable("id") Long ProductId,
            @RequestBody Productimage productimage) {
        productimage.setId(ProductId);
        Productimage updatedProduct = producimageService.updateProductimage(productimage);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long ProductId) {
        producimageService.deleteProductimage(ProductId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Productimage>> getProductImagesByProductId(@PathVariable Long productId) {
        List<Productimage> productImages = producimageService.getProductImagesByProductId(productId);
        if (productImages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }

}




