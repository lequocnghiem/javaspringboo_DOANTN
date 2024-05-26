package com.api.thuctaptotnghiepbackend.Request;

import java.util.Set;

import com.api.thuctaptotnghiepbackend.Entity.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterEmail {
    private String username;
    private String email;
    private String password;
    private String address;
    private String role;
    private String name;
    private String status;
    private byte[] image;
    private String phone;

}

