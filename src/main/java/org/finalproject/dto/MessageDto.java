package org.finalproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class MessageDto {

    private Long id;
    private String content;
    private Long chatId;
    private Long userId;
}
