package com.api.thuctaptotnghiepbackend.Repository.Product;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Productsale;

@Repository
public interface ProductsaleRepository extends JpaRepository<Productsale, Long> {
     Page<Productsale> findAll(Pageable pageable);
     Productsale findByProductId(Long productId);
}
