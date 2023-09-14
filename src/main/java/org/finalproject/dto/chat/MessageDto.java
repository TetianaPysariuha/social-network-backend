package org.finalproject.dto.chat;

import lombok.*;
import org.apache.catalina.LifecycleState;
import org.finalproject.dto.UserDto;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageDto {

    // @JsonProperty("message_id")
    private Long id;
    private String content;
    private UserForChatDto sender;
    private List<MessageImageDto> imageUrls;

    //    @JsonManagedReference
    // private ChatDto chat;
    // @JsonProperty("chat_id")
    private Long chatId;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
