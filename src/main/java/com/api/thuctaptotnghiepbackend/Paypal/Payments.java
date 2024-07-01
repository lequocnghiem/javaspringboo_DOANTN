package com.api.thuctaptotnghiepbackend.Paypal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments") // Chỉ định tên bảng trong cơ sở dữ liệu
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId;
    private String payerId;
    private double amount;
    private String idUser;
    private String productname;
    private String idProduct;
    private String currency;
    private String method;
    private String quantity;
    private String intent;
    private String color;
    private String size;
    private String description;
    private Date paymentTime;
    private String status;
    private String shippingStatus;

   
}

