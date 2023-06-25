package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.finalproject.repository.ChatRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Service
public class DefaultChatService {

    private ChatRepository chatRepository;

    public List<Message> findByContent(String content) {

        List<Message> messageList = chatRepository.findByContent(content);
        List<Chat> chatList = new ArrayList<>();
        Chat chat;
        for (Message message : messageList){
            chat = chatRepository.findById(message.getChatId());
            chatList.add(chat);

        }
        return null;

    }

    public List<User> findByName ()
}
