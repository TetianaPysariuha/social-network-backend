package org.finalproject.dto.chat;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Message;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDtoMapper extends GeneralFacade<Message, MessageDtoRequest, MessageDto> {

    private final UserForChatDtoMapper userDtoMapper;
    private final MessageImageDtoMapper messageImageDtoMapper;


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
        return dto;
    }

}





