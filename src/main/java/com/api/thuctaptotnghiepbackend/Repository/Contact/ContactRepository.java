package com.api.thuctaptotnghiepbackend.Repository.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAll(Pageable pageable);
}
