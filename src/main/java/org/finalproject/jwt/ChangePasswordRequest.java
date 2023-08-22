package org.finalproject.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class ChangePasswordRequest {

    private String code;
    private String newPassword;

}
