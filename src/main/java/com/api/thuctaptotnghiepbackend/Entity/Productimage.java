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
@Table(name = "productimages")

public class Productimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[]  imageData; 
    private boolean isPrimary;

    
    @ManyToOne
	@JoinColumn(name = "productId")

	private Product product;
  




  
  
    public boolean getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

   

   
}





