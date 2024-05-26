package com.api.thuctaptotnghiepbackend.LoginToken;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("jwt")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class Logintoken {
    

    
@GetMapping("/login")
public ResponseEntity<String> Loginadmin() {
    return ResponseEntity.ok("login admin  succeded");
}
    
}
