package org.finalproject.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserImageDto {

    private String imgUrl;
    private Long userId;

}
