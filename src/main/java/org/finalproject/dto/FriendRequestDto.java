package org.finalproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendRequestDto {
    @NotNull
    @Min(1)
    private Long id;
    private String status;
    private Long userID;
    private Long friendID;
}
