package org.finalproject.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class JwtRequest {

    private String email;
    private String password;

}
