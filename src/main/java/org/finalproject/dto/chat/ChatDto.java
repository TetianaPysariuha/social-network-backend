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

    private Long id;
    private List<MessageDto> messages;
    private List<UserForChatDto> users;

}
