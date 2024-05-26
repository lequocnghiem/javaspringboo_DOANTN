package com.api.thuctaptotnghiepbackend.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Builder
@Setter
public class LoginRequest {
    private String email;
    private String password;
}
