package com.api.thuctaptotnghiepbackend.Entity;


import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
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
   
   
  
    @ManyToOne
	@JoinColumn(name ="categoryId")

	private Category category;


    @ManyToOne
	@JoinColumn(name = "brandId")

	private Brand brand;


    
    @ManyToMany
    private Set<ProductColor> colors = new HashSet<>();
    
    @ManyToMany
    private Set<ProductSize> sizes = new HashSet<>();


//     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//       @JsonIgnore
//      private List<Productimage> ProductimageList;
   

//      @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//      @JsonIgnore
//     private List<Productoption> Productoptionlist;

//     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//     @JsonIgnore
//    private List<Productoptionvalue> Productoptionvaluelist;


//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//   private List<Productsale> Productsalelist;

//   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//   @JsonIgnore
//  private List<Productstore> Productstorelist;

//     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//     @JsonIgnore
//    private List<Cart> CartList;
   
}