package com.api.thuctaptotnghiepbackend.JWT;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {

    private static final String SECRET_KEY = "123";

    public String generateToken(User user, Collection<SimpleGrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("address", user.getAddress())
                .withClaim("phone", user.getPhone())
                .withClaim("userId", user.getId())
                .withClaim("verified", user.isVerified())
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateRefreshToken(User user, Collection<SimpleGrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
              
                .sign(algorithm);
    }
}