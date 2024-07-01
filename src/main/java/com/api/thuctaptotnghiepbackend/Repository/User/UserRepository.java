package com.api.thuctaptotnghiepbackend.Repository.User;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
       Page<User> findAll(Pageable pageable);
       Optional<User> findByEmail(String email);
       User findByUsername(String username);
     
}

