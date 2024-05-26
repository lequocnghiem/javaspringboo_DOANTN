package com.api.thuctaptotnghiepbackend.Service.Ipml;



import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Slider;
import com.api.thuctaptotnghiepbackend.Repository.Slider.SliderRepository;
import com.api.thuctaptotnghiepbackend.Service.SliderService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SliderServiceImpl implements SliderService { // Rename the class

    private SliderRepository sliderRepository; // Rename the repository

    @Override
    public Slider createSlider(Slider slider) {
        return sliderRepository.save(slider);
    }

    @Override
    public Slider getSliderById(Long sliderId) {
        Optional<Slider> optionalSlider = sliderRepository.findById(sliderId);
        return optionalSlider.orElse(null);
    }

    @Override
    public Page<Slider> getAllSliders(Pageable pageable) {
        return sliderRepository.findAll(pageable);
    }

    @Override
    public Slider updateSlider(Slider slider) {
        Slider existingSlider = sliderRepository.findById(slider.getId()).orElse(null);
        if (existingSlider != null) {
            existingSlider.setName(slider.getName());
          
     
            existingSlider.setPostion(slider.getPostion());
            existingSlider.setCreatedAt(slider.getCreatedAt());
            existingSlider.setUpdatedAt(slider.getUpdatedAt());
           
            existingSlider.setStatus(slider.getStatus());
            existingSlider.setImage(slider.getImage());
            Slider updatedSlider = sliderRepository.save(existingSlider);
            return updatedSlider;
        }
        return null;
    }

    @Override
    public void deleteSlider(Long sliderId) {
        sliderRepository.deleteById(sliderId);
    }
}