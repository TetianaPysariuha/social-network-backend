package org.finalproject.dto;

import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper extends GeneralFacade<User, UserRequestDto,UserDto> {

    UserImageDtoMapper userImageDtoMapper;

    @Override
    protected void decorateDto(UserDto dto, User entity) {

        List<UserImageDto> userImageDto  = entity.getUserImages()
                .stream().map(userImageDtoMapper::convertToDto)
                .collect(Collectors.toList());

        dto.setUserImages(userImageDto);


    }
}