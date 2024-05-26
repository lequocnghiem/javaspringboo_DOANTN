package com.api.thuctaptotnghiepbackend.Controller.Slider;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.thuctaptotnghiepbackend.Entity.Slider;
import com.api.thuctaptotnghiepbackend.Service.SliderService;



@RestController
@AllArgsConstructor
@RequestMapping("api/sliders") // Change the mapping
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class SliderController { // Rename the class

    private SliderService sliderService; // Rename the service

    // Create Slider REST API
    @PostMapping
    public ResponseEntity<Slider> createSlider(@RequestBody Slider slider) { // Rename the method
       
        Slider savedSlider = sliderService.createSlider(slider); // Rename the service method
        return new ResponseEntity<>(savedSlider, HttpStatus.CREATED);
    }

   

    // Get Slider by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Slider> getSliderById(@PathVariable("id") Long sliderId) { // Rename the method and parameter
        Slider slider = sliderService.getSliderById(sliderId); // Rename the service method
        if (slider != null) {
            return new ResponseEntity<>(slider, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All Sliders REST API
    @GetMapping
    public ResponseEntity<Page<Slider>> getAllSliders(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Slider> sliders = sliderService.getAllSliders(pageable); // Rename the service method
        return new ResponseEntity<>(sliders, HttpStatus.OK);
    }

    // Update Slider REST API
    @PutMapping("{id}")
    public ResponseEntity<Slider> updateSlider(@PathVariable("id") Long sliderId,
            @RequestBody Slider slider) { // Rename the method and parameter
        slider.setId(sliderId);
        Slider updatedSlider = sliderService.updateSlider(slider); // Rename the service method
        return new ResponseEntity<>(updatedSlider, HttpStatus.OK);
    }

    // Delete Slider REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSlider(@PathVariable("id") Long sliderId) { // Rename the parameter
        sliderService.deleteSlider(sliderId); // Rename the service method
        return new ResponseEntity<>("Slider successfully deleted!", HttpStatus.OK);
    }
}