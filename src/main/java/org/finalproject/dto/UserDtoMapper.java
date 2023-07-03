package org.finalproject.dto;

import org.finalproject.entity.User;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper extends GeneralFacade<User, UserRequestDto,UserDto> {

}