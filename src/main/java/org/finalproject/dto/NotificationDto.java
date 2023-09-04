package org.finalproject.dto;

import jakarta.persistence.*;
import lombok.*;
import org.finalproject.entity.User;
import org.finalproject.util.NotificationType;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NotificationDto {


    private String type;

    private String status;


    private String senderFullName;


    private String content;


    private User receiver;
}
