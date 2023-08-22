package org.finalproject.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RefreshJwtRequest {

    public String refreshToken;

}
