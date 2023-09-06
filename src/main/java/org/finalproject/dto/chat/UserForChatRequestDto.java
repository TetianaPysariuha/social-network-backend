package org.finalproject.dto.chat;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserForChatRequestDto {

    @NotNull
    @Min(1)
    @Max(1000)
    private Long id;
    @NotNull
    @Size(min = 4, message = "user name should have at least 4 characters")
    private String fullName;
    private String profilePicture;

}
