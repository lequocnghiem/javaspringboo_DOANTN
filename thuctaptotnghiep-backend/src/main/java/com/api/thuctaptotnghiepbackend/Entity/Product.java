package com.api.thuctaptotnghiepbackend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
    
 
    @Id
    private String id;
    private String name;
    private String slug;
    private double  price;
    private int qty;
    private String description;
    private String metakey;
    private String metadesc;
  
    private LocalDateTime createdAt;
    
    
    private LocalDateTime updatedAt;
    private int status;
    private String categoryId;
    private String brandId;



   



  
    
   
}