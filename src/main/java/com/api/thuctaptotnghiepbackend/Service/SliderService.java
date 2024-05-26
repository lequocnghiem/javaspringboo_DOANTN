package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Slider;



public interface SliderService {
    Slider createSlider(Slider slider);

    Slider getSliderById(Long sliderId);

    Page<Slider> getAllSliders(Pageable pageable);

    Slider updateSlider(Slider slider);

    void deleteSlider(Long sliderId);
    
}