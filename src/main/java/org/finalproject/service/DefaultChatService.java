package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.ChatDto;
import org.finalproject.dto.ChatSpecDto;
import org.finalproject.entity.Chat;
import org.finalproject.entity.ChatSpecProjection;
import org.finalproject.entity.Message;
import org.finalproject.repository.ChatRepository;
import org.finalproject.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultChatService extends GeneralService<Chat> {

    private final ChatRepository chatRepository;
//    private MessageRepository messageRepository;
//
//    public List<Chat> findByContent(String content) {
//
//        List<Message> messageList = messageRepository.findByContent(content);
//        List<Chat> chatList = new ArrayList<>();
//        Chat chat;
//        for (Message message : messageList) {
//            Optional<Chat> chat1 = chatRepository.findById(message.getChatId());
//            chat = chat1.get();
//            chatList.add(chat);
//        }
//        return chatList;
//    }
//
//    public List<Chat> findUserIdByPartOfName(String partOfName) {
//
//        List<Long> usersId = chatRepository.findUserIdByPartOfName(partOfName);
//        return chatRepository.findAllById(usersId);
//    }

    public List<ChatSpecDto> getChatsForUserExceptUserId(Long userId) {
        List<ChatSpecProjection> projections = chatRepository.getChatsForUserExceptUserId(userId);
        return projections.stream()
                .map(ChatSpecDto::fromProjection)
                .collect(Collectors.toList());
    }

}
