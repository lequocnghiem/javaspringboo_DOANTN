package com.api.thuctaptotnghiepbackend.Repository.Slider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Slider;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {
    Page<Slider> findAll(Pageable pageable);
}
