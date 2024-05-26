package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Productoption;

public interface ProductoptionSevice {
    


     Productoption createOption(Productoption productoption);
     Productoption getProductById(Long productId);
     Page<Productoption> getAllProducts(Pageable pageable);
     Productoption updateProduct(Productoption productoption);
     void deleteProduct(Long productId);
}
