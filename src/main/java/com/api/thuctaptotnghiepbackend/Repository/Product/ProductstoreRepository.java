package com.api.thuctaptotnghiepbackend.Repository.Product;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.api.thuctaptotnghiepbackend.Entity.Productstore;
@Repository
public interface ProductstoreRepository extends JpaRepository<Productstore, Long> {
       Page<Productstore> findAll(Pageable pageable);
}

