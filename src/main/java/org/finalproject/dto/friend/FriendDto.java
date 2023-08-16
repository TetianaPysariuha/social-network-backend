package org.finalproject.dto.friend;

import lombok.*;
import org.finalproject.dto.UserDto;

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
    private UserDto friend;
    private List<UserDto> mutualFriends;

}
