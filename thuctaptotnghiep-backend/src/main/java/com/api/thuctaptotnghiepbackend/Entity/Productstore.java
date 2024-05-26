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
@Document(collection = "productstore")
public class Productstore {
    @Id
    private String id;

    // ID của sản phẩm liên quan
    private String productId;

    private int quantityAdded;
    private LocalDateTime entryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int createdBy;
    private Integer updatedBy;
    private int status;

    // Các phương thức getter và setter
}
