package com.api.thuctaptotnghiepbackend.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "db_user")
public class User {

    @Id
    private String id;
    private String name;
    private byte[] image;
    private String phone;
    private String username;
    private String password;
    private String address;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String role;

    // Các phương thức getter và setter
}
