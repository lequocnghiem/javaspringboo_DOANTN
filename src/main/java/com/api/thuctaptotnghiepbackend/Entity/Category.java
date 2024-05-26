package com.api.thuctaptotnghiepbackend.Entity;


import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;

    private String parentId;
    private long sortOrder;
    private String metakey;
    private String metadesc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long createdBy;
    private Long updatedBy;
    private int status;


    
    //     @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
     
    //  private List<Product> categorylist;
    // Getter và Setter
    // Getter và Sette
}