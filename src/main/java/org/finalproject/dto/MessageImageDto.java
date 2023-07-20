package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageImageDto {

    private Long id;
    private String imgUrl;
    private Message messageId;


}