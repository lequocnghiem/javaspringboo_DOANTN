package com.api.thuctaptotnghiepbackend.Entity;







import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

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
