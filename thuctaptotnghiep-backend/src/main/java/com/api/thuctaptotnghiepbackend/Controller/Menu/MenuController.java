package com.api.thuctaptotnghiepbackend.Controller.Menu;


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

import com.api.thuctaptotnghiepbackend.Entity.Menu;
import com.api.thuctaptotnghiepbackend.Service.MenuService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/menu")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class MenuController {
      private  MenuService MenuService ;

    @PostMapping
    public ResponseEntity<Menu> createBrands(@RequestBody Menu brand) {
          brand.setCreated_at(LocalDateTime.now());
        Menu savedBrand = MenuService.createBrand(brand);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> getBrandById(@PathVariable("id") String brandId) {
        Menu brand = MenuService.getBrandById(brandId);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Menu>> getAllBrands(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Menu> brandPage = MenuService.getAllBrands(pageable);
        return new ResponseEntity<>(brandPage, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Menu> updateBrand(@PathVariable("id") String brandId,
            @RequestBody Menu brand) {
        brand.setId(brandId);
          brand.setUpdated_at(LocalDateTime.now());
        Menu updatedBrand = MenuService.updateBrand(brand);
        return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") String brandId) {
        MenuService.deleteBrand(brandId);
        return new ResponseEntity<>("Brand successfully deleted!", HttpStatus.OK);
    }
}

