package org.finalproject.util;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.finalproject.jwt.JwtAuthentication;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();

        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }


    @RequiredArgsConstructor
    public static enum Roles implements GrantedAuthority {
        USER("USER");
        private final String value;

        @Override
        public String getAuthority() {

            return value;
        }
    }
}

