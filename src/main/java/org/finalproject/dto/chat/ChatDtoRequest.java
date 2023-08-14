package org.finalproject.dto.chat;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.finalproject.dto.UserDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ChatDtoRequest {

    @NotNull
    private Long id;
    private List<MessageDto> messages;
    private List<MessageImageDto> messageImages;
    @NotNull
    private List<UserDto> users;

}
