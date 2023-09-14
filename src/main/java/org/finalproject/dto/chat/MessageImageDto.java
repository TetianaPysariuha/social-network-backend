package org.finalproject.dto.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageImageDto {

    //@JsonProperty("message_image_id")
    private Long id;
    private String imgUrl;
    private MessageDto messageId;

}