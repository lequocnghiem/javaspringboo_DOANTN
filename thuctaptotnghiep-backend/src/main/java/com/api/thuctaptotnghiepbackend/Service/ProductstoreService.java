package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Productstore;

public interface ProductstoreService {

    Productstore createProductstore(Productstore productstore);

    Productstore getProductstoreById(String productstoreId);

    Page<Productstore> getAllProductstores(Pageable pageable);

    Productstore updateProductstore(Productstore productstore);

    void deleteProductstore(String productstoreId);
}

