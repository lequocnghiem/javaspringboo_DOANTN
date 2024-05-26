package com.api.thuctaptotnghiepbackend.Repository.Cart;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Cart;
import com.api.thuctaptotnghiepbackend.Entity.Product;

@Repository
public interface CartReepository  extends JpaRepository<Cart, Long>  {
  Optional<Cart> findByProductIdAndUserId(Long productId, Long userId);
      List<Cart> findByUserId(Long userId);
      Cart findByProductIdAndUserIdAndSizeAndColor(Long productId, Long userId, String size, String color);
} 
