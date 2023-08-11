package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.MessageSearchDto;
import org.finalproject.entity.Message;
import org.finalproject.entity.MessageSearchProjection;
import org.finalproject.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMessageService extends GeneralService<Message> {

    private final MessageRepository messageRepository;

    public List<MessageSearchDto> findByContent(String content, Long userId) {

        List<MessageSearchProjection> projections = messageRepository.findByContent(content, userId);
        return projections.stream()
                .map(MessageSearchDto::fromProjection)
                .collect(Collectors.toList());
    }
}
