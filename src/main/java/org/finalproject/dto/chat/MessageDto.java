package org.finalproject.dto.chat;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageDto {

    private Long id;
    private String content;
    private UserForChatDto sender;
    private List<MessageImageDto> imageUrls;
    private Long chatId;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private Boolean status;
}
