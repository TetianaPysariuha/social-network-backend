package org.finalproject.dto;

import org.finalproject.entities.User;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper extends GeneralFacade<User, UserRequestDto,UserDto> {

}