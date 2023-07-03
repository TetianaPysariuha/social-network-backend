package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.finalproject.repository.ChatRepository;
import org.finalproject.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultChatService extends GeneralService<Chat> {

    private ChatRepository chatRepository;
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


}
