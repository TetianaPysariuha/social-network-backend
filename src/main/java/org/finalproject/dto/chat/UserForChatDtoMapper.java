package org.finalproject.dto.chat;

import org.finalproject.dto.UserDto;
import org.finalproject.dto.UserRequestDto;
import org.finalproject.entity.User;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class UserForChatDtoMapper extends GeneralFacade<User, UserForChatRequestDto, UserForChatDto> {

}