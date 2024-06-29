package com.api.thuctaptotnghiepbackend.chat.repository;

import com.api.thuctaptotnghiepbackend.chat.entity.ChatMessage;
import com.api.thuctaptotnghiepbackend.chat.entity.EMessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, EMessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, EMessageStatus status);
}