package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;

public interface ProductoptionvalueService {

     Productoptionvalue createOption(Productoptionvalue productoptionvalue);
     Productoptionvalue getProductById(String productId);
     Page<Productoptionvalue> getAllProducts(Pageable pageable);
     Productoptionvalue updateProduct(Productoptionvalue productoptionvalue);
     void deleteProduct(String productId);
} 