package org.finalproject.dto.chat;

import lombok.*;
import org.finalproject.entity.UserSpecProjection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserSpecDto {

    private Long id;

    private String fullName;

    private String profilePicture;

    public static UserSpecDto fromProjection (UserSpecProjection projection){
        return new UserSpecDto(
                projection.getId(), projection.getFullName(), projection.getProfilePicture());
    }

}
