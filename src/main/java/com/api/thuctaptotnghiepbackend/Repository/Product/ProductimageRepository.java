package com.api.thuctaptotnghiepbackend.Repository.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Productimage;

@Repository
public interface ProductimageRepository extends JpaRepository<Productimage,Long>  {
    Page<Productimage> findAll(Pageable pageable);
    
    List<Productimage> findByProductId(Long productId);
    List<Productimage> findByProductIdAndIsPrimary(Long productId, boolean isPrimary);
}
