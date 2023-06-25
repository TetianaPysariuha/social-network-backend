package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.PostTypes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostDto {

    private PostTypes postType;
    private String content;

}
