package com.api.thuctaptotnghiepbackend.Repository.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;

import java.util.List;
import java.util.Optional;




public interface RoleRepository extends JpaRepository<Role, Long> {
    Page<Role> findAll(Pageable pageable);
    Optional<Role> findByName(String name);
    
} 
