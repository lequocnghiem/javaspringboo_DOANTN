package com.api.thuctaptotnghiepbackend.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer quantity;
    private Integer price;
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "shipping_order_id") // This should match the name of the column in your database
    private ShippingOrder shippingOrder;
   
    // Constructors, getters, setters
}