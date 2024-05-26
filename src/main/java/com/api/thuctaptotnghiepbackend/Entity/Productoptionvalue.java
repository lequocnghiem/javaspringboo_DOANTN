package com.api.thuctaptotnghiepbackend.Entity;


import java.time.LocalDateTime;

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
@Table(name = "productoptionvalue")

public class Productoptionvalue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "productId")
   
	private Product product;

    @ManyToOne
	@JoinColumn(name = "optionId")
   
    private Productoption productoption;
 
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Các phương thức getter và setter

}