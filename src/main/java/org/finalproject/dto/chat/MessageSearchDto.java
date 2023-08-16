package org.finalproject.dto.chat;

import lombok.*;
import org.finalproject.entity.MessageSearchProjection;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageSearchDto {

    private Long id;
    private String content;
    private Long chatId;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private Long userId;
    private String fullName;
    private String profilePicture;

    public static MessageSearchDto fromProjection(MessageSearchProjection projection) {

        return new MessageSearchDto(
                projection.getId(), projection.getContent(), projection.getChatId(), projection.getCreatedBy(),
                projection.getCreatedDate(), projection.getUpdatedBy(), projection.getUpdatedDate(),
                projection.getUserId(), projection.getFullName(), projection.getProfilePicture()
        );
    }
}
