package com.api.thuctaptotnghiepbackend.Entity;


import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "productoption")

public class Productoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

      @ManyToOne
	@JoinColumn(name = "productId")
     
	private Product product;
    
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Các phương thức getter và setter
//  @OneToMany(mappedBy = "productoption", cascade = CascadeType.ALL, orphanRemoval = true)
//       @JsonIgnore
//      private List<Productoptionvalue> Productoptionvalue;
   
}
