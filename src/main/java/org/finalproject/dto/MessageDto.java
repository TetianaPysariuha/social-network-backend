package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.Chat;
import org.finalproject.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageDto {

    private Long id;
    private String content;
    private User sender;
    private Chat chat;

}
