package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.Post;
import org.finalproject.entity.PostImage;
import org.finalproject.entity.PostTypes;
import org.finalproject.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostDto {

    private Long id;
    private UserDto user;
    private String postType;
    private String content;
    private PostDto parentId;
    private List<UserDto> likes;
    private Set<UserDto> reposts;
    private List<PostImage> postImages;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private List<CommentDto> comments;
}
