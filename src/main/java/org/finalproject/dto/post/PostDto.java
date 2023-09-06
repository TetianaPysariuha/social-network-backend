package org.finalproject.dto.post;

import lombok.*;
import org.finalproject.dto.UserDto;
import org.finalproject.entity.PostImage;

import java.util.Date;
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
    private Set<UserDto> repostsUsers;
    private List<RepostDto> reposts;

    private List<PostImage> postImages;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private List<CommentDto> comments;
}
