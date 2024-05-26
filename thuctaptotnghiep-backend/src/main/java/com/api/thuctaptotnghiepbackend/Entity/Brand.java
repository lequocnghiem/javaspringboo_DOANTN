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
@Document(collection = "brands")
public class Brand {
    @Id
    private String id;

    private String name;
    private String slug;
    private byte[] image;
    private int sortOrder;
    private String metakey;
    private String metadesc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int createdBy;
    private Integer updatedBy;
    private int status;

    // Getter và Setter
}
