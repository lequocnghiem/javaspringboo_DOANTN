package com.api.thuctaptotnghiepbackend.Entity;


import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // MongoDB equivalent of @GeneratedValue(strategy = GenerationType.IDENTITY)

      @ManyToOne
	@JoinColumn(name = "topicId")
  
	private Topic Topic;

 

    private String title;

    private String detail;

    private byte[] image;

    private String type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String status;
}