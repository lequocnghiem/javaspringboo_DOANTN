package com.api.thuctaptotnghiepbackend.Entity;


import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;


    @ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
  
 
    private double price;
    private String color;
    private int qty;
    private String size;


    @ManyToOne
	@JoinColumn(name = "userId") 
	private User user;
 
}
