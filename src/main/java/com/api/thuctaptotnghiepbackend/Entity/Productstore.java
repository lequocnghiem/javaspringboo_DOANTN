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
@Table(name = "productstore")

public class Productstore {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID của sản phẩm liên quan
    @ManyToOne
	@JoinColumn(name = "productId")
     @JsonIgnore
	private Product product;

    private int quantityAdded;
    private LocalDateTime entryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int createdBy;
    private Integer updatedBy;
    private int status;

    // Các phương thức getter và setter
}
