package com.api.thuctaptotnghiepbackend.Request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Roleaddtouser {
    private String email;
    private List<String> rolename; 
}



