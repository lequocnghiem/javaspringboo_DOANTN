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
@Document(collection = "categories")
public class Category {
    @Id
    private String id;

    private String name;
    private String slug;
    private String image;
    private String parentId;
    private long sortOrder;
    private String metakey;
    private String metadesc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long createdBy;
    private Long updatedBy;
    private int status;

    // Getter và Sette
}