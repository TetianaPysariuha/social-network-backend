package org.finalproject.dto.friend;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FriendRequestCreateDto {
    @NotNull
    private Long friendID;
    @Nullable
    @Pattern(regexp = "^(removed|$)")
    private String status;
}
