package com.api.thuctaptotnghiepbackend.Controller.ProductColorandsize;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Service.ProductcolorService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("api/productcolor")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductColorController {

    @Autowired
    private ProductcolorService productColorService;

    @GetMapping
    public List<ProductColor> getAllProductColors() {
        return productColorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductColor> getProductColorById(@PathVariable Long id) {
        Optional<ProductColor> productColor = productColorService.getById(id);
        return productColor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductColor> addProductColor(@RequestBody ProductColor productColor) {
        ProductColor newProductColor = productColorService.add(productColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductColor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductColor> updateProductColor(@PathVariable Long id, @RequestBody ProductColor updatedProductColor) {
        ProductColor modifiedProductColor = productColorService.update(id, updatedProductColor);
        return ResponseEntity.ok(modifiedProductColor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductColor(@PathVariable Long id) {
        productColorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
