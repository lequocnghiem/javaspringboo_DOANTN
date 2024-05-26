package com.api.thuctaptotnghiepbackend.Entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productimages")
public class Productimage {
     @Id
    private String id;

    
    private byte[]  imageData; 
    private boolean isPrimary;

    
    private String productId; // Trường này chứa id của Product




  
  
    public boolean getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

   

   
}





