package org.finalproject.dto.chat;

import lombok.*;
import org.finalproject.dto.UserDto;

import java.util.Date;

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
    private UserDto sender;

    //    @JsonManagedReference
    // private ChatDto chat;
    // @JsonProperty("chat_id")
    private Long chatId;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
