package org.finalproject.dto;

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
    private List<MessageImageDto> messageImages;
    private List<UserDto> users;
}
