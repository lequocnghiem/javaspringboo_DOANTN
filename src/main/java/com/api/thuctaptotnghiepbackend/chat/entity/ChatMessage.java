package com.api.thuctaptotnghiepbackend.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : it.baonk
 * @mailto : khacbao.1007@gmail.com
 * @created : 6/29/2024, Saturday
 **/
@Getter
@Setter
@Entity
@ToString
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private EMessageStatus status;
}
