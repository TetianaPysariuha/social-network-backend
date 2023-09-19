
package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.UserImage;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ImgCommentDto  {

   private Long id;

    private UserDto author;


    private String content;


    private UserImage image;


    private Date createdDate;



}