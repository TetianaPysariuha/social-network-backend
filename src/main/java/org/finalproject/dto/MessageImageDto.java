package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.Chat;

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
    private Chat chat;

}
