package com.api.thuctaptotnghiepbackend.Repository.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Entity.ProductInventory;
import com.api.thuctaptotnghiepbackend.Entity.ProductSize;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {

      Page<ProductInventory> findAll(Pageable pageable);
    ProductInventory findByProductAndColorAndSize(Product product, ProductColor color, ProductSize size);
    List<ProductInventory> findByProductId(Long productId);
    Optional<ProductInventory> findByProductIdAndColorIdAndSizeId(Long productId, Long colorId, Long sizeId);
    // Các phương thức CRUD khác sẽ được kế thừa từ JpaRepository
}