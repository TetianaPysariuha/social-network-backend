package org.finalproject.dto;

import org.finalproject.entity.Chat;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class ChatDtoMapper extends GeneralFacade<Chat, ChatDtoRequest, ChatDto> {

}
