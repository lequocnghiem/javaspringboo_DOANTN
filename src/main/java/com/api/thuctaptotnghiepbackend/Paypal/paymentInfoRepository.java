package com.api.thuctaptotnghiepbackend.Paypal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface paymentInfoRepository extends JpaRepository<PaymentInfo,Long> {

    
}

