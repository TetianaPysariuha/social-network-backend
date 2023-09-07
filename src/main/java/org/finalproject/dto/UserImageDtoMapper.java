package org.finalproject.dto;

import org.finalproject.entity.UserImage;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class UserImageDtoMapper extends GeneralFacade<UserImage, UserImageDtoRequest, UserImageDto> {
    @Override
    protected void decorateDto(UserImageDto dto, UserImage entity) {

        dto.setImgUrl(entity.getImageUrl());


    }

}
