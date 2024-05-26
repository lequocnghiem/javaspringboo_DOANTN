package com.api.thuctaptotnghiepbackend.Repository.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.thuctaptotnghiepbackend.Entity.Productoption;

public interface ProductoptionRepository extends MongoRepository<Productoption,String> {
    Page<Productoption> findAll(Pageable pageable);
    

}
