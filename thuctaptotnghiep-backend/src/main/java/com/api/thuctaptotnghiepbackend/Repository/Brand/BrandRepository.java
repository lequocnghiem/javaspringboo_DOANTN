package com.api.thuctaptotnghiepbackend.Repository.Brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Brand;


@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
      Page<Brand> findAll(Pageable pageable);
}

