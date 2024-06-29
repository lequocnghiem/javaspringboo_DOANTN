package com.api.thuctaptotnghiepbackend.chat.service;

import com.api.thuctaptotnghiepbackend.chat.entity.ChatMessage;
import com.api.thuctaptotnghiepbackend.chat.entity.EMessageStatus;
import com.api.thuctaptotnghiepbackend.chat.repository.ChatMessageRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author : it.baonk
 * @mailto : khacbao.1007@gmail.com
 * @created : 6/29/2024, Saturday
 **/
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChatMessageService {
    ChatMessageRepository repository;
    ChatRoomService chatRoomService;

    public ChatMessageService(ChatMessageRepository repository, ChatRoomService chatRoomService) {
        this.repository = repository;
        this.chatRoomService = chatRoomService;
    }

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        return repository.saveAndFlush(chatMessage);
    }

    public Long countNewMessages(Long senderId, Long recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, EMessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(Long recipientId, Long senderId) {
        log.info("====>Start findChatMessages By SenderId: {}, recipientId: {}", senderId, recipientId);
        var chatRoom = chatRoomService.getChatId(senderId, recipientId, false);
        log.info("====>Rooom Chat: {}", chatRoom);
        var messages =
                chatRoom.map(repository::findByChatId).orElse(new ArrayList<>());
        log.info("====>Size Message: {}", messages.size());
        if (!messages.isEmpty()) {
            var chatMessages = repository.findBySenderIdAndRecipientIdAndStatus(senderId, recipientId, EMessageStatus.DELIVERED);
            chatMessages.forEach(item -> {
                updateOnField(item.getId(), chatMessage -> chatMessage.setStatus(EMessageStatus.RECEIVED));
            });
        }
        log.info("====>Find Message OK");
        return messages;
    }

    public ChatMessage updateOnField(Long id, Consumer<ChatMessage> entityConsumer) {
        ChatMessage entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Chat Message"));
        return this.update(entity, entityConsumer);
    }

    private ChatMessage update(ChatMessage entity, Consumer<ChatMessage> entityConsumer) {
        entityConsumer.accept(entity);
        return repository.save(entity);
    }

    public ChatMessage findChatMessageById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Chat Message"));
    }
}
