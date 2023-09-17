package org.finalproject.dto.chat;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Message;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDtoMapper extends GeneralFacade<Message, MessageDtoRequest, MessageDto> {

    private final UserForChatDtoMapper userDtoMapper;
    private final MessageImageDtoMapper messageImageDtoMapper;
    @Autowired
    private DefaultMessageService defaultMessageService;


    public MessageDto decorateDto(Message entity) {

        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSender(userDtoMapper.convertToDto(entity.getSender()));
        List<MessageImageDto> messageImageDtos = (entity.getImages() == null)
                ? new ArrayList<>()
                : entity.getImages().stream().map(messageImageDtoMapper::convertToDto).toList();
        dto.setImageUrls(messageImageDtos);
        dto.setChatId(entity.getChat().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setStatus(defaultMessageService.findUnReadMessages(entity.getId()));
        return dto;
    }

}





