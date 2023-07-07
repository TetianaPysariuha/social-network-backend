package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.*;

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

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    List <User> likes;
    List <Repost> reposts;
}
