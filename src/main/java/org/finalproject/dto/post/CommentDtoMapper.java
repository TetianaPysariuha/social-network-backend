package org.finalproject.dto.post;

import org.finalproject.entity.Post;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class CommentDtoMapper extends GeneralFacade<Post, CommentRequestDto, CommentDto> {

}
