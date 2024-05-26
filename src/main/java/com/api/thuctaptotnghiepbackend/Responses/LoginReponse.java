package com.api.thuctaptotnghiepbackend.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReponse {
    private String token;
    private String refreshToken;
}
