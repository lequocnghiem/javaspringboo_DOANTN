package com.api.thuctaptotnghiepbackend.Service;

import java.util.List;
import java.util.Optional;

import com.api.thuctaptotnghiepbackend.Entity.ProductSize;

public interface ProductsizeService {
    List<ProductSize> getAll();
    Optional<ProductSize> getById(Long id);
    ProductSize add(ProductSize productSize);
    ProductSize update(Long id, ProductSize updatedProductSize);
    void delete(Long id);
    
}