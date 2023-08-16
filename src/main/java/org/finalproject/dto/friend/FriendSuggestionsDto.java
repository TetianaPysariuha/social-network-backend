package org.finalproject.dto.friend;

import lombok.*;
import org.finalproject.entity.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendSuggestionsDto {
    private User friend;
    private List<User> mutualFriends;
}
