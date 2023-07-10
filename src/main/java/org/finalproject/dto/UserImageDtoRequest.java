package org.finalproject.dto;

import jakarta.validation.constraints.NotNull;

public class UserImageDtoRequest {

    private String imgUrl;
    @NotNull
    private Long userId;


}
