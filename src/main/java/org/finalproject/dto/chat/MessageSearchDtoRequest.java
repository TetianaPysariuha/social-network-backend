package org.finalproject.dto.chat;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageSearchDtoRequest {

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
}
