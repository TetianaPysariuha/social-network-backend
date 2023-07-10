package org.finalproject.dto;

import org.finalproject.entity.Post;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class PostDtoMapper extends GeneralFacade<Post, PostRequestDto, PostDto> {

}
