package com.api.thuctaptotnghiepbackend.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;

public interface ProductoptionvalueService {

     Productoptionvalue createOption(Productoptionvalue productoptionvalue);
     Productoptionvalue getProductById(Long productId);
     Page<Productoptionvalue> getAllProducts(Pageable pageable);
     Productoptionvalue updateProduct(Productoptionvalue productoptionvalue);
     void deleteProduct(Long productId);
     // List<Productoptionvalue> findProductsByValue(List<Long> values);
} 