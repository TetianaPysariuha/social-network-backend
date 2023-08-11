package org.finalproject.entity;

import java.util.Date;

public interface MessageSearchProjection {

    Long getId();

    String getContent();

    Long getChatId();

    String getCreatedBy();

    Date getCreatedDate();

    String getUpdatedBy();

    Date getUpdatedDate();

    Long getUserId();

    String getFullName();

    String getProfilePicture();
}
