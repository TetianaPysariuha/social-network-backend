package org.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.finalproject.entity.Chat;

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
    private Long messageId;
    @NotNull
    private Chat chat;
}
