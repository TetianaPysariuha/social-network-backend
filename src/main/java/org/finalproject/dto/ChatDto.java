package org.finalproject.dto;

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
public class ChatDto {

    private Long id;
    private List<Message> messages;
    private List<MessageImage> messageImages;
    private List<User> users;

}
