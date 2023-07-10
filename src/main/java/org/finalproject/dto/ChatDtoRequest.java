package org.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.finalproject.entity.Message;
import org.finalproject.entity.MessageImage;
import org.finalproject.entity.User;

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
    private List<Message> messages;
    private List<MessageImage> messageImages;
    @NotNull
    private List<User> users;
}
