package com.api.thuctaptotnghiepbackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.api.thuctaptotnghiepbackend.JWT.JwtloginFilter;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.Arrays;
// import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtloginFilter jwtloginFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/ws/**").permitAll() 
                .requestMatchers("/api/**").permitAll() 
                .and()
            // .authorizeHttpRequests()
            //     .requestMatchers("/demo/**").hasAnyAuthority("ROLE_USER") 
            //     .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/cart/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/cart/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/cart/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/cart/**").authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers( "jwt/login/**").hasAnyAuthority("ROLE_USER")
                .and()



                .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtloginFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin() // trả về page login nếu chưa authenticate
                .and()
            .build();
    }
    

   
}