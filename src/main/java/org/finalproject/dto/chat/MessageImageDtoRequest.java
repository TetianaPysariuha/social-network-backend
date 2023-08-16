package org.finalproject.dto.chat;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageImageDtoRequest {

    @NotNull
    private Long id;
    @NotNull
    private String imgUrl;
    @NotNull
    private MessageDto messageId;
    @NotNull
    private ChatDto chat;
}
