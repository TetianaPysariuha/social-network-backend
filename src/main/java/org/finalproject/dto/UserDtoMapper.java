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


    protected UserDto decorateDto( User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setFullName(entity.getFullName());
        userDto.setEmail(entity.getEmail());
        userDto.setCity(entity.getCity());
        userDto.setCountry(entity.getCountry());
        userDto.setAbout(entity.getAbout());
        userDto.setProfilePicture(entity.getProfilePicture());
        userDto.setProfileBackgroundPicture(entity.getProfileBackgroundPicture());
        userDto.setBirthDate(entity.getBirthDate());
        userDto.setGender(entity.getGender());

        List<UserImageDto> userImageDto  = entity.getUserImages()
                .stream().map(userImageDtoMapper::convertToDto)
                .collect(Collectors.toList());

        userDto.setUserImages(userImageDto);
        return userDto;


    }
}