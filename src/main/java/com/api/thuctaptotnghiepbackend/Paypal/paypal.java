package com.api.thuctaptotnghiepbackend.Paypal;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Getter
@Entity
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paypal")

public class paypal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payment_id")
    //  @JsonIgnore
	private PaymentInfo paymentInfo;


    
    private String productId;

   
    private String productname;


    private String quantity;

   
    private String idUser;

   
    private String amount;
    private String color;
    private String size;

    private String order_code;
    
    private String currency;

}
