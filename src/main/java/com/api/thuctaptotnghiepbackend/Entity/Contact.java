package com.api.thuctaptotnghiepbackend.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use String as the type for MongoDB ObjectId

    
    @ManyToOne
	@JoinColumn(name = "userId")
    private User user;

    private String name;
    private String email;
    private String phone;
    private String title;
    private String content;




    private LocalDateTime  createdAt;
    private LocalDateTime  updatedAt;

  

    private Integer status;

    // Add any additional MongoDB-specific annotations or customizations as needed
}
