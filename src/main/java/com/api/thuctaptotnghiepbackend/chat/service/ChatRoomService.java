package com.api.thuctaptotnghiepbackend.chat.service;

import com.api.thuctaptotnghiepbackend.chat.entity.ChatRoom;
import com.api.thuctaptotnghiepbackend.chat.repository.ChatRoomRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : it.baonk
 * @mailto : khacbao.1007@gmail.com
 * @created : 6/29/2024, Saturday
 **/
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatRoomService {
    ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        log.info("====>Start getChatId id: {}, recipientId: {}", senderId, recipientId);
        ChatRoom chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).orElse(null);
        if (chatRoom != null) {
            return Optional.of(chatRoom.getChatId());

        } else {

            if (!createIfNotExist) {
                return Optional.empty();
            }
            var chatId =
                    String.format("%s_%s", senderId, recipientId);

            ChatRoom senderRecipient = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            ChatRoom recipientSender = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();
            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);
            log.info("====>Start getChatId id: {}, recipientId: {} OK", senderId, recipientId);
            return Optional.of(chatId);
        }
    }
}
