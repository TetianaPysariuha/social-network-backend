package org.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ChatSpecDtoRequest {

    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    private String fullName;
    private String content;
    private Date lastMessageDate;
}
