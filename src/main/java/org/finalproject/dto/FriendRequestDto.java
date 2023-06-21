package org.finalproject.dto;

import lombok.*;
import org.finalproject.entities.User;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendRequestDto {
    private Long id;
    private String status;

    private User user;

    private User friend;
}
