package org.finalproject.dto.chat;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ChatDto {

    // @JsonProperty("chat_id")
    private Long id;
    //    @JsonBackReference
    private List<MessageDto> messages;
    private List<MessageImageDto> messageImages;
    private List<UserForChatDto> users;

}
