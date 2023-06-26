package org.finalproject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.finalproject.entities.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendRequestDto {
    @NotNull
    @Min(1)
    @Max(1000)
    private Long id;
    private String status;

    private User user;

    private User friend;
}
