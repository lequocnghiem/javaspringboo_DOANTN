package com.api.thuctaptotnghiepbackend.Paypal;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface paymentInfoRepository extends JpaRepository<PaymentInfo,Long> {

    List<PaymentInfo> findByidUser(String userId);
    List<PaymentInfo> findByPaymentId(String paymentId);

     @Query("SELECT new com.api.thuctaptotnghiepbackend.Paypal.SalesData(MONTH(p.paymentTime), SUM(p.amount)) FROM PaymentInfo p GROUP BY MONTH(p.paymentTime)")
    List<SalesData> findSalesData();
    List<PaymentInfo> findTop5ByOrderByPaymentTimeDesc(Pageable pageable);
}

