package com.api.thuctaptotnghiepbackend.Service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.api.thuctaptotnghiepbackend.Entity.Productsale;

public interface ProductsaleService {

    Productsale createProductsale(Productsale productsale);

    Productsale getProductsaleById(Long productsaleId);

    Page<Productsale> getAllProductsales(Pageable pageable);

    Productsale updateProductsale(Productsale productsale);

    void deleteProductsale(Long productsaleId);
    Productsale findSaleByProductId(Long productId);
}

