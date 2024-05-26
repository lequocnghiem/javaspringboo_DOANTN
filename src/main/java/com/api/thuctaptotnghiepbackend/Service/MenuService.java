package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Menu;

public interface MenuService {

    Menu createBrand(Menu Menu);

    Menu getBrandById(Long menuId);

    Page <Menu> getAllBrands(Pageable pageable);

    Menu updateBrand(Menu Menu);

    void deleteBrand(Long menuId);
} 
