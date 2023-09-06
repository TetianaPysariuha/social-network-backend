package org.finalproject.dto.post;

import lombok.*;
import org.finalproject.dto.UserDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RepostDto {
    private Long id;
    private UserDto user;
    private String postType;
    private String content;
    private PostDto parentId;
    private List<UserDto> likes = new ArrayList<>();
    private List<RepostDto> reposts = new ArrayList<>();
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private List<CommentDto> comments = new ArrayList<>();
}
