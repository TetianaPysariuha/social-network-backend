package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class FriendDto {

    private Long id;
    private String status;

    private User user;

    private  User friend;

}
