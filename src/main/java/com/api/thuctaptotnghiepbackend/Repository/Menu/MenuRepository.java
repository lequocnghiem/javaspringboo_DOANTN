
package com.api.thuctaptotnghiepbackend.Repository.Menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Menu;




@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
      Page<Menu> findAll(Pageable pageable);
}
