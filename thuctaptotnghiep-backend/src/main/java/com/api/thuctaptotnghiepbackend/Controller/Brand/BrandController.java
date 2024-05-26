package com.api.thuctaptotnghiepbackend.Controller.Brand;

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

import com.api.thuctaptotnghiepbackend.Entity.Brand;
import com.api.thuctaptotnghiepbackend.Service.BrandService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/brand")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class BrandController {
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand savedBrand = brandService.createBrand(brand);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") String brandId) {
        Brand brand = brandService.getBrandById(brandId);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Brand>> getAllBrands(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brandPage = brandService.getAllBrands(pageable);
        return new ResponseEntity<>(brandPage, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") String brandId,
            @RequestBody Brand brand) {
        brand.setId(brandId);
        Brand updatedBrand = brandService.updateBrand(brand);
        return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") String brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseEntity<>("Brand successfully deleted!", HttpStatus.OK);
    }
}

