package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Message;
import org.finalproject.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMessageService extends GeneralService<Message> {

    private MessageRepository messageRepository;

}
