package com.api.thuctaptotnghiepbackend.Repository.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Product;

public interface ProductRepository extends MongoRepository<Product,String> {
    Page<Product> findAll(Pageable pageable);
    

}