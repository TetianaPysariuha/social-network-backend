package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.User;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class NotificationRequestDto {

    private Long id;

    private String type;


    private String senderFullName;


    private String content;



}
