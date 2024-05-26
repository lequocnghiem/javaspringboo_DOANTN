package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Brand;
import com.api.thuctaptotnghiepbackend.Repository.Brand.BrandRepository;
import com.api.thuctaptotnghiepbackend.Service.BrandService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getBrandById(String brandId) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        return optionalBrand.orElse(null);
    }

    @Override
    public Page<Brand> getAllBrands(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }
    @Override
    public Brand updateBrand(Brand brand) {
        Optional<Brand> existingBrand = brandRepository.findById(brand.getId());
        if (existingBrand.isPresent()) {
            Brand updatedBrand = existingBrand.get();
            // Cập nhật tất cả các trường của Brand
            updatedBrand.setName(brand.getName());
            updatedBrand.setSlug(brand.getSlug());
            updatedBrand.setImage(brand.getImage());
            updatedBrand.setSortOrder(brand.getSortOrder());
            updatedBrand.setMetakey(brand.getMetakey());
            updatedBrand.setMetadesc(brand.getMetadesc());
            updatedBrand.setCreatedAt(brand.getCreatedAt());
            updatedBrand.setUpdatedAt(brand.getUpdatedAt());
            updatedBrand.setCreatedBy(brand.getCreatedBy());
            updatedBrand.setUpdatedBy(brand.getUpdatedBy());
            updatedBrand.setStatus(brand.getStatus());
            
            return brandRepository.save(updatedBrand);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBrand(String brandId) {
        brandRepository.deleteById(brandId);
    }
}
