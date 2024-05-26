package com.api.thuctaptotnghiepbackend.Controller.Product;

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

import com.api.thuctaptotnghiepbackend.Entity.Productsale;

import com.api.thuctaptotnghiepbackend.Service.ProductsaleService;

import lombok.AllArgsConstructor;




@RestController
@AllArgsConstructor
@RequestMapping("api/productsale")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductsaleController {

    private ProductsaleService productsaleService;

    @PostMapping
    public ResponseEntity<Productsale> createProductSale(@RequestBody Productsale productsale) {

        
        Productsale savedProductSale = productsaleService.createProductsale(productsale);
        return new ResponseEntity<>(savedProductSale, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Productsale> getProductSaleById(@PathVariable("id") String productsaleId) {
        Productsale productsale = productsaleService.getProductsaleById(productsaleId);

        if (productsale != null) {
            return new ResponseEntity<>(productsale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



      
    @GetMapping
    public ResponseEntity<Page<Productsale>> getAllProductSales(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Productsale> productsalePage = productsaleService.getAllProductsales(pageable);

        return new ResponseEntity<>(productsalePage, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Productsale> updateProductSale(@PathVariable("id") String productsaleId,
            @RequestBody Productsale productsale) {
        productsale.setId(productsaleId);
        Productsale updatedProductSale = productsaleService.updateProductsale(productsale);
        return new ResponseEntity<>(updatedProductSale, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductSale(@PathVariable("id") String productsaleId) {
        productsaleService.deleteProductsale(productsaleId);
        return new ResponseEntity<>("Productsale successfully deleted!", HttpStatus.OK);
    }
}

