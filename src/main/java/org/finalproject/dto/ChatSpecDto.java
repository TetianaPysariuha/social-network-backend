package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.ChatSpecProjection;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ChatSpecDto {

    private Long id;
    private Long userId;
    private String fullName;
    private String content;
    private Date lastMessageDate;
    private String profilePicture;

    public static ChatSpecDto fromProjection(ChatSpecProjection projection) {
        return new ChatSpecDto(
                projection.getId(), projection.getUserId(),
                projection.getFullName(), projection.getContent(),
                projection.getLastMessageDate(), projection.getProfilePicture()
        );
    }


}
