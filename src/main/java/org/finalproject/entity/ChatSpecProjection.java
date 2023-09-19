package org.finalproject.entity;

import org.finalproject.dto.chat.UserForChatDto;

import java.util.Date;
import java.util.List;

public interface ChatSpecProjection {

    Long getId();

    Long getUserId();

    String getFullName();

    String getContent();

    Long getMessageId();

    Date getLastMessageDate();

    String getProfilePicture();
    Long getMessageCount();

    List<UserForChatDto> getChatParticipant();

}
