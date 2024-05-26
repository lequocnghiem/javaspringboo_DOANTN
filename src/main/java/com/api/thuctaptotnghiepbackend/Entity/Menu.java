package com.api.thuctaptotnghiepbackend.Entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String link;
    private String parent_id;
 private String postion;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
 
    
    private Integer status;

    // Getter và Sette
}