package com.api.thuctaptotnghiepbackend.JWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import org.springframework.http.MediaType;

@Component
@RequiredArgsConstructor
public class JwtloginFilter  extends OncePerRequestFilter{

	
	private static final String Secret_key="123";
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	String loginHeader=request.getHeader(AUTHORIZATION);
    if(loginHeader!=null && loginHeader.startsWith("Bearer ")){
        try {
            String token=loginHeader.substring("Bearer ".length());
            Algorithm algorithm=Algorithm.HMAC256(Secret_key.getBytes());
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            String username = decodedJWT.getSubject();
            String [] roles=decodedJWT.getClaim("roles").asArray(String.class);

Collection <SimpleGrantedAuthority> logCollection =new ArrayList<>();
Arrays.stream(roles).forEach(role->{
    logCollection.add(new SimpleGrantedAuthority(role));
});

UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(username,null,logCollection);
SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

filterChain.doFilter(request, response);
        } catch (Exception e) {
          response.setHeader("error",e.getMessage());
          response.setStatus(FORBIDDEN.value());
          Map<String,String> error =new HashMap<>();
error.put("error_message", e.getMessage());
response.setContentType(MediaType.APPLICATION_JSON_VALUE);
new ObjectMapper().writeValue(response.getOutputStream(), error);
          
        }
    }else{
        filterChain.doFilter(request, response);
    }
		
	}
    
}
