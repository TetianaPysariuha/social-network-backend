package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendDto {

    private Long id;
    private String status;
    private User friend;
    private List<User> mutualFriends;

}
