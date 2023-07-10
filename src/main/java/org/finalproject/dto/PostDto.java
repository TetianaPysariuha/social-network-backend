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
    private User user;
    private String postType;
    private String content;
    private Post parentId;
    private Set<User> likes;
    private Set<User> reposts;
    private List<PostImage> postImages;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    List<Post> comments;
}
