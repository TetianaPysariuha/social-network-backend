package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.ChatSpecDto;
import org.finalproject.entity.Chat;
import org.finalproject.entity.ChatSpecProjection;
import org.finalproject.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultChatService extends GeneralService<Chat> {

    private final ChatRepository chatRepository;

    public List<ChatSpecDto> getChatsForUserExceptUserId(Long userId) {

        List<ChatSpecProjection> projections = chatRepository.getChatsForUserExceptUserId(userId);
        return projections.stream()
                .map(ChatSpecDto::fromProjection)
                .collect(Collectors.toList());
    }

}
