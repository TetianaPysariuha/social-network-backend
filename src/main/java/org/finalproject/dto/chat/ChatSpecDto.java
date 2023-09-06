package org.finalproject.dto.chat;

import lombok.*;
import org.finalproject.entity.ChatSpecProjection;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ChatSpecDto {

    //@JsonProperty("chat_id")
    private Long id;
    private Long userId;
    private String fullName;
    private String content;
    private Date lastMessageDate;
    private String profilePicture;
    private List<UserForChatDto> chatParticipant; //del autorized user and create new DTO

    public static ChatSpecDto fromProjection(ChatSpecProjection projection) {

        return new ChatSpecDto(
                projection.getId(), projection.getUserId(),
                projection.getFullName(), projection.getContent(),
                projection.getLastMessageDate(), projection.getProfilePicture(), projection.getChatParticipant()
        );
    }


}
