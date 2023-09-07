package org.finalproject.dto.post;

import lombok.*;
import org.finalproject.dto.UserDto;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommentDto {

    private Long id;
    private UserDto user;
    private String postType;
    private String content;
    private PostDto parentId;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}

