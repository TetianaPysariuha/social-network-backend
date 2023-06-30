package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.Post;
import org.finalproject.entity.PostTypes;
import org.finalproject.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequestDto {
    private User user;
    private String postType;
    private String content;
    private Post parentId;
}
