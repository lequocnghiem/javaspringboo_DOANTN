
package com.api.thuctaptotnghiepbackend.Repository.Menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Menu;




@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {
      Page<Menu> findAll(Pageable pageable);
}
