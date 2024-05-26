package com.api.thuctaptotnghiepbackend.Entity;


import java.time.LocalDateTime;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "slider")

public class Slider {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use String as the type for MongoDB ObjectId

    private String name;
   
    private int postion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int status;
    private byte[] image;

    // Add any additional MongoDB-specific annotations or customizations as needed
}