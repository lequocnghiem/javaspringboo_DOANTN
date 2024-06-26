package com.api.thuctaptotnghiepbackend.Repository.Brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
      Page<Brand> findAll(Pageable pageable);
}

