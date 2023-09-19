package org.finalproject.dto;

import org.finalproject.entity.ImgComment;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgCommentDtoMapper extends GeneralFacade<ImgComment, ImgCommentDtoRequest, ImgCommentDto> {
    @Autowired
    DefaultUserImageService imageService;

    @Autowired
    DefaultUserService userService;


    @Autowired
    private UserDtoMapper dtoMapper;

    @Autowired
    private UserImageDtoMapper imageMapper;


    @Override
    public void decorateDto(ImgCommentDto dto, ImgComment entity) {

        dto.setAuthor(dtoMapper.convertToDto(entity.getAuthor()));
        dto.setImage(imageMapper.convertToDto(entity.getImage()));

    }

    @Override
    public void decorateEntity(ImgComment entity, ImgCommentDtoRequest dto) {

       entity.setAuthor(userService.getOne(dto.getAuthorId()));
        entity.setImage(imageService.getOne(dto.getImageId()));

    }
}

