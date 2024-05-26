package com.api.thuctaptotnghiepbackend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productoption")
public class Productoption {

    @Id
    private String id;

   
    private String productId; // ID của sản phẩm liên quan
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Các phương thức getter và setter

}
