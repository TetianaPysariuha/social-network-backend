package org.finalproject.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordRequest {

    private String code;
    private String newPassword;

}
