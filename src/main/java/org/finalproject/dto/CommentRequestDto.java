package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.Post;
import org.finalproject.entity.PostImage;
import org.finalproject.entity.User;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommentRequestDto {

    private Long id;
    private User user;
    private String postType;
    private String content;
    private Post parentId;
    private List<User> likes;
    private Set<User> reposts;
    private List<PostImage> postImages;
}
