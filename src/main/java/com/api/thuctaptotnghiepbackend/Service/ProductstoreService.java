package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Productstore;

public interface ProductstoreService {

    Productstore createProductstore(Productstore productstore);

    Productstore getProductstoreById(Long productstoreId);

    Page<Productstore> getAllProductstores(Pageable pageable);

    Productstore updateProductstore(Productstore productstore);

    void deleteProductstore(Long productstoreId);
}

