package com.api.thuctaptotnghiepbackend.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menu")
public class Menu {
    @Id
    private String id;

    private String name;
    private String link;
    private String parent_id;
 
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
 
    
    private Integer status;

    // Getter và Sette
}