
package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Menu;
import com.api.thuctaptotnghiepbackend.Repository.Menu.MenuRepository;

import com.api.thuctaptotnghiepbackend.Service.MenuService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private  MenuRepository MenuRepository ;

    @Override
    public Menu createBrand(Menu Menu) {
        return MenuRepository.save(Menu);
    }

    @Override
    public Menu getBrandById(Long MenuId) {
        Optional<Menu> optionalBrand = MenuRepository.findById(MenuId);
        return optionalBrand.orElse(null);
    }

    @Override
    public Page<Menu> getAllBrands(Pageable pageable) {
        return MenuRepository.findAll(pageable);
    }
    @Override
    public Menu updateBrand(Menu Menu) {
        Optional<Menu> existingBrand = MenuRepository.findById(Menu.getId());
        if (existingBrand.isPresent()) {
             Menu updatedMenu = existingBrand.get();
            // Update all fields of Menu
            updatedMenu.setName(Menu.getName());
            updatedMenu.setParent_id(Menu.getParent_id());
           updatedMenu.setPostion(Menu.getPostion());
              updatedMenu.setLink(Menu.getLink());
            updatedMenu.setCreated_at(Menu.getCreated_at());
            updatedMenu.setUpdated_at(Menu.getUpdated_at());
            updatedMenu.setStatus(Menu.getStatus());
           

            return MenuRepository.save(updatedMenu);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBrand(Long menuId) {
        MenuRepository.deleteById(menuId);
    }
}
