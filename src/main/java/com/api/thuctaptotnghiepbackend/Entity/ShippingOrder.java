package com.api.thuctaptotnghiepbackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idOrder;

    @Column(name = "payment_type_id")
    private Integer payment_type_id = 1; // Default value 2

    @Column(name = "required_note")
    private String required_note = "CHOXEMHANGKHONGTHU"; // Default value

    @Column(name = "from_name")
    private String from_name;

    @Column(name = "from_phone")
    private String from_phone;

    @Column(name = "from_address")
    private String from_address;

    @Column(name = "from_district_name")
    private String from_district_name;

    @Column(name = "from_ward_name")
    private String from_ward_name;

    @Column(name = "from_province_name")
    private String from_province_name;

    @Column(name = "to_name")
    private String to_name;

    @Column(name = "to_phone")
    private String to_phone;

    @Column(name = "to_address")
    private String to_address;

    @Column(name = "to_ward_code", columnDefinition = "varchar(255) default '20308'")
    private String to_ward_code;
    

    @Column(name = "to_district_id")
    private Integer to_district_id = 1444; // Default value 1444

    // Optional fields
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "service_type_id")
    private Integer service_type_id = 2; 

    @Column(name = "service_id")
    private Integer service_id = 0; // Default value 0

    @ElementCollection
    @CollectionTable(name = "pick_shift", joinColumns = @JoinColumn(name = "shipping_order_id"))
    @Column(name = "shift_id")
    private List<Integer> pickShift = Collections.singletonList(2); // Default value [2]

    // Define relationship many-to-one with Item
    @OneToMany(mappedBy = "shippingOrder", cascade = CascadeType.ALL)
    private List<Item> items;
    // Constructors, getters, setters
}
