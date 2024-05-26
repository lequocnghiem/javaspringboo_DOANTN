package com.api.thuctaptotnghiepbackend.Service;

import java.util.List;
import java.util.Optional;

import com.api.thuctaptotnghiepbackend.Entity.ProductColor;

public interface ProductcolorService {

    List<ProductColor> getAll();
    Optional<ProductColor> getById(Long id);
    ProductColor add(ProductColor productColor);
    ProductColor update(Long id, ProductColor updatedProductColor);
    void delete(Long id);
} 