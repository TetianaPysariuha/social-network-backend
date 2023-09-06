package org.finalproject.dto;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.ChatDtoRequest;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.repository.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PostDtoMapper extends GeneralFacade<Post, PostRequestDto, PostDto> {

    private UserDtoMapper userDtoMapper;
    private CommentDtoMapper commentDtoMapper;

    public PostDto decorateDto(Post entity) {
        PostDto dto = new PostDto();
        dto.setId(entity.getId());
        dto.setUser(decorateDtoUser(entity.getUser()));
        dto.setPostType(entity.getPostType());
        dto.setContent(entity.getContent());
        dto.setParentId(decorateDtoPost(entity.getParentId()));
        dto.setLikes(entity.getLikes().stream().map(this::decorateDtoUser).collect(Collectors.toList()));
        dto.setReposts(entity.getReposts().stream().map(this::decorateDtoUser).collect(Collectors.toSet()));
        dto.setPostImages(entity.getPostImages());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setComments(entity.getComments().stream().map(this::decorateDtoComment).collect(Collectors.toList()));
        return dto;
    }

    protected UserDto decorateDtoUser(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setBirthDate(entity.getBirthDate());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setGender(entity.getGender());
        dto.setWorkPlace(entity.getWorkPlace());
        dto.setProfilePicture(entity.getProfilePicture());
        dto.setAbout(entity.getAbout());
        dto.setProfileBackgroundPicture(entity.getProfileBackgroundPicture());
        return dto;
    }

    protected CommentDto decorateDtoComment(Post entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setUser(decorateDtoUser(entity.getUser()));
        dto.setPostType(entity.getPostType());
        dto.setContent(entity.getContent());
        dto.setParentId(decorateDtoPost(entity.getParentId()));
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

    protected PostDto decorateDtoPost(Post entity) {
        if (entity == null) {
            return null;
        }
        PostDto dto = new PostDto();
        dto.setId(entity.getId());
        dto.setUser(decorateDtoUser(entity.getUser()));
        dto.setPostType(entity.getPostType());
        dto.setContent(entity.getContent());
        dto.setParentId(decorateDtoPost(entity.getParentId()));
        dto.setPostImages(entity.getPostImages());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }
}
