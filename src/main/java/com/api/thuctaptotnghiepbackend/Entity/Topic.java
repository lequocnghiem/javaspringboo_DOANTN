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
@Table(name = "topic")

public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // MongoDB equivalent of @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private String slug;

   

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    private int status;


}