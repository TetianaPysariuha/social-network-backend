package org.finalproject.controller;


import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.finalproject.entity.User;
import org.finalproject.jwt.JwtResponse;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.service.jwt.AuthService;
import org.finalproject.service.jwt.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@Validated
class Oauth2Controller {

    private final AuthService authService;

    private final UserJpaRepository repository;

    private final JwtProvider jwtProvider;


    @GetMapping("oauth2/search")
    Optional<User> search(@RequestParam String email, OAuth2Authentication authentication) {
        //String auth = (String) authentication.;
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        return repository.getByEmail(email);
    }

    @GetMapping("/oauth2/authorization/google")
    public ResponseEntity<?>  login( ) throws IOException {

        String access = authService.getRefreshStorage().get("token");

        final Claims claims = jwtProvider.getAccessClaims(access);
        final String email = claims.getSubject();
        String refresh = jwtProvider.generateOauthRefreshToken(email);
        authService.loginAuth2(email,refresh);
        final JwtResponse token = new JwtResponse(access,refresh);

        return ResponseEntity.ok(token);


    }


}
