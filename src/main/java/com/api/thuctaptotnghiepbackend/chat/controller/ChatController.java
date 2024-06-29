package com.api.thuctaptotnghiepbackend.chat.controller;

import com.api.thuctaptotnghiepbackend.chat.entity.ChatMessage;
import com.api.thuctaptotnghiepbackend.chat.service.ChatMessageService;
import com.api.thuctaptotnghiepbackend.chat.service.ChatRoomService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : it.baonk
 * @mailto : khacbao.1007@gmail.com
 * @created : 6/29/2024, Saturday
 **/
@RestController
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    SimpMessagingTemplate messagingTemplate;
    ChatRoomService chatRoomService;
    ChatMessageService chatMessageService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatRoomService chatRoomService,
                          ChatMessageService chatMessageService) {
        this.messagingTemplate = messagingTemplate;
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());
        ChatMessage saved = chatMessageService.saveChatMessage(chatMessage);
        log.info("====> Send Chat Message ChatId: {}, MsgId: {}, SendId:{}, SendName: {}, RecivedId: {}",
                chatId, saved.getId(), saved.getSenderId(), saved.getSenderName(), chatMessage.getRecipientId());
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()), "/queue/messages",
                saved);
    }
}
