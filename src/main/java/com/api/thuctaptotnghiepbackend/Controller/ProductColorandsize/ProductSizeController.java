package com.api.thuctaptotnghiepbackend.Controller.ProductColorandsize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.thuctaptotnghiepbackend.Entity.ProductSize;
import com.api.thuctaptotnghiepbackend.Service.ProductsizeService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor

@RestController
@RequestMapping("api/productsize")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductSizeController {

    @Autowired
    private ProductsizeService productSizeService;

    @GetMapping
    public List<ProductSize> getAllProductSizes() {
        return productSizeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSize> getProductSizeById(@PathVariable Long id) {
        Optional<ProductSize> productSize = productSizeService.getById(id);
        return productSize.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductSize> addProductSize(@RequestBody ProductSize productSize) {
        ProductSize newProductSize = productSizeService.add(productSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSize> updateProductSize(@PathVariable Long id, @RequestBody ProductSize updatedProductSize) {
        ProductSize modifiedProductSize = productSizeService.update(id, updatedProductSize);
        return ResponseEntity.ok(modifiedProductSize);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductSize(@PathVariable Long id) {
        productSizeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
