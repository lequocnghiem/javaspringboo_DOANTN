package com.api.thuctaptotnghiepbackend.Entity;


import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "productsale")

public class Productsale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @ManyToOne
	@JoinColumn(name = "productId")
  
	private Product product;

    private int salePrice;
    private int quantitySold;
    private Date   dateStart;
    private Date   dateEnd;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int createdBy;
    private Integer updatedBy;
   
   
}
