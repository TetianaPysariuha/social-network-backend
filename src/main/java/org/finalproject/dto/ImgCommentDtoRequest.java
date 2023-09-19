package org.finalproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")

public class ImgCommentDtoRequest  {

    private Long id;

    private Long authorId;


    private String content;



    private Long imageId;



}