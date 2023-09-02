package org.finalproject.dto.chat;

import lombok.*;
import org.finalproject.entity.UserSpecProjection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserForChatDto {

    private Long id;

    private String fullName;

    private String profilePicture;

    public static UserForChatDto fromProjection(UserSpecProjection projection) {

        return new UserForChatDto(
                projection.getId(), projection.getFullName(), projection.getProfilePicture());
    }

}
