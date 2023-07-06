package org.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.finalproject.entity.Chat;
import org.finalproject.entity.User;

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
    private Chat chatId;
    @NotNull
    private User sender;
}
