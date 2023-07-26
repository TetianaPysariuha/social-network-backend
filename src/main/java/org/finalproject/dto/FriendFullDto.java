package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendFullDto {
    private Long id;
    private String status;

    private User friend;

    private User user;
}
