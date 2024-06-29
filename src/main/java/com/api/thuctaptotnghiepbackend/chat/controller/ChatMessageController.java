package com.api.thuctaptotnghiepbackend.chat.controller;

import com.api.thuctaptotnghiepbackend.chat.entity.ChatMessage;
import com.api.thuctaptotnghiepbackend.chat.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : it.baonk
 * @mailto : khacbao.1007@gmail.com
 * @created : 6/29/2024, Saturday
 **/
@RestController
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/chat/messages")
public class ChatMessageController {
    ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages(@PathVariable Long senderId,
                                              @PathVariable Long recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(recipientId, senderId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessage> findMessage(@PathVariable Long id) {
        return ResponseEntity.ok(chatMessageService.findChatMessageById(id));
    }
}
