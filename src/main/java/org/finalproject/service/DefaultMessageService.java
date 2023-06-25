package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.finalproject.entity.Message;
import org.finalproject.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Service
public class DefaultMessageService extends GeneralService<Message> {

    private MessageRepository messageRepository;

    public List<Message> findByContent(String content){
        return messageRepository.findByContent(content);
    }



}
