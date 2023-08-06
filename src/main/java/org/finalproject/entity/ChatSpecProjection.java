package org.finalproject.entity;

import java.util.Date;

public interface ChatSpecProjection {

    Long getId();

    Long getUserId();

    String getFullName();

    String getContent();

    Date getLastMessageDate();

}
