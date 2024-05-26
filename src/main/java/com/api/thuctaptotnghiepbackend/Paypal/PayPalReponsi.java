package com.api.thuctaptotnghiepbackend.Paypal;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PayPalReponsi extends JpaRepository<paypal,Long> {
    List<paypal> findByidUser(String userId);
    
}
