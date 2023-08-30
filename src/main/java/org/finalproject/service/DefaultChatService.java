package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.ChatSpecDto;
import org.finalproject.dto.chat.UserSpecDto;
import org.finalproject.entity.Chat;
import org.finalproject.entity.ChatSpecProjection;
import org.finalproject.entity.UserSpecProjection;
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

        List<ChatSpecProjection> projections = chatRepository.getChatsForUser(userId);
        List<ChatSpecDto> chatSpecDtoList = projections.stream()
                .map(ChatSpecDto::fromProjection).toList();
        for (ChatSpecDto chat : chatSpecDtoList) {
            chat.setChatParticipant(findUsersFromChat(chat.getId()));
        }
        return chatSpecDtoList;
    }

    public List<Chat> findChatsByParticipant(Long userId, Long loggedUserId) {

        return chatRepository.findChatsByParticipant(userId, loggedUserId);
    }

    public List<UserSpecDto> findUsersFromChat(Long chatId) {

        List<UserSpecProjection> projections = chatRepository.findUsersFromChat(chatId);
        return projections.stream()
                .map(UserSpecDto::fromProjection)
                .collect(Collectors.toList());
    }

}
