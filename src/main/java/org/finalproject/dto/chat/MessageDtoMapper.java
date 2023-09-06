package org.finalproject.dto.chat;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.UserDtoMapper;
import org.finalproject.entity.Message;
import org.finalproject.facade.GeneralFacade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageDtoMapper extends GeneralFacade<Message, MessageDtoRequest, MessageDto> {

    private final UserForChatDtoMapper userDtoMapper;
    private final ChatDtoMapper chatDtoMapper;
    private final ModelMapper modelMapper;

    public MessageDto decorateDto(Message entity) {

        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSender(userDtoMapper.convertToDto(entity.getSender()));
        dto.setChatId(entity.getChat().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

}





