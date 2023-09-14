package org.finalproject.dto.chat;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.repository.ChatRepository;
import org.finalproject.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatDtoMapper extends GeneralFacade<Chat, ChatDtoRequest, ChatDto> {

    private final UserForChatDtoMapper userDtoMapper;
    private final MessageImageDtoMapper messageImageDtoMapper;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    protected void decorateEntity(Chat entity, ChatDtoRequest dto) {

        entity.setId(dto.getId());
        entity.setMessages(chatRepository.getById(dto.getId()).getMessages());
        entity.setUsers(chatRepository.getById(dto.getId()).getUsers());
        entity.setMessageImages(chatRepository.getById(dto.getId()).getMessageImages());
    }


    public ChatDto decorateDto(Chat entity) {

        ChatDto dto = new ChatDto();
        dto.setId(entity.getId());
        dto.setMessages(entity.getMessages().stream().map(this::decorateDtoM).collect(Collectors.toList()));
        dto.setMessageImages(entity.getMessageImages().stream().map(messageImageDtoMapper::convertToDto).collect(Collectors.toList()));
        dto.setUsers(entity.getUsers().stream().map(userDtoMapper::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    protected MessageDto decorateDtoM(Message entity) {

        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSender(userDtoMapper.convertToDto(entity.getSender()));
        List<MessageImageDto> messageImageDtos = (entity.getImages() == null)
                ? entity.getImages().stream().map(messageImageDtoMapper::convertToDto).toList()
                : new ArrayList<>();
        dto.setImageUrls(messageImageDtos);
        dto.setChatId(entity.getChatId());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        return dto;
    }
}
