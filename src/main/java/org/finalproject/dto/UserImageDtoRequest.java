package org.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserImageDtoRequest {
    @NotNull
    private Long id;
    @NotNull
    private String imageUrl;
    @NotNull
    private Long userId;


}
