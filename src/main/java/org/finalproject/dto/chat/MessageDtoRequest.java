package org.finalproject.dto.chat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.finalproject.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageDtoRequest {

    @NotNull
    private Long id;
    @NotNull
    @Size(min = 1, message = "message content should have at least 1 characters")
    private String content;

    @NotNull
    private UserDto sender;

    @NotNull
    private ChatDto chat;

}
