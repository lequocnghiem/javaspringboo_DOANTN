package com.api.thuctaptotnghiepbackend.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long senderId;
    private Long receiverId;
    private String content;

  

   
}