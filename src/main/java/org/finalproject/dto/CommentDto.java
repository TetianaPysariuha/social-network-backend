package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommentDto {

    private Long id;
    private User user;
    private String postType;
    private String content;
    private CommentDto parentId;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}

