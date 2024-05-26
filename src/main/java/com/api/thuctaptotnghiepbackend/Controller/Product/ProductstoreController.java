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
import com.api.thuctaptotnghiepbackend.Entity.Productstore;
import com.api.thuctaptotnghiepbackend.Service.ProductstoreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/productstore")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductstoreController {

    private ProductstoreService productstoreService;

    @PostMapping
    public ResponseEntity<Productstore> createProductStore(@RequestBody Productstore productstore) {
          productstore.setCreatedAt(LocalDateTime.now());
        Productstore savedProductStore = productstoreService.createProductstore(productstore);
        return new ResponseEntity<>(savedProductStore, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Productstore> getProductStoreById(@PathVariable("id") Long productstoreId) {
        Productstore productstore = productstoreService.getProductstoreById(productstoreId);

        if (productstore != null) {
            return new ResponseEntity<>(productstore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Productstore>> getAllProductStores(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Productstore> productstorePage = productstoreService.getAllProductstores(pageable);

        return new ResponseEntity<>(productstorePage, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Productstore> updateProductStore(@PathVariable("id") Long productstoreId,
            @RequestBody Productstore productstore) {
        productstore.setId(productstoreId);
        Productstore updatedProductStore = productstoreService.updateProductstore(productstore);
        return new ResponseEntity<>(updatedProductStore, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductStore(@PathVariable("id") Long productstoreId) {
        productstoreService.deleteProductstore(productstoreId);
        return new ResponseEntity<>("Productstore successfully deleted!", HttpStatus.OK);
    }
}
