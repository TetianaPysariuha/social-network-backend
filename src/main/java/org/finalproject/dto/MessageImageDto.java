package org.finalproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageImageDto {

    private Long id;
    private String imgUrl;
    private Long messageId;

}
