package org.finalproject.dto;

import org.finalproject.entity.Message;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class MessageDtoMapper extends GeneralFacade<Message, MessageDtoRequest, MessageDto> {

}
