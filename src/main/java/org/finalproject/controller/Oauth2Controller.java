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

        String role = authentication.getAuthorities().iterator().next().getAuthority();

        return repository.getByEmail(email);
    }

    @GetMapping("/oauth2/authorization/google")
    public ResponseEntity<?>  login( ) throws IOException {

        String access = authService.getRefreshStorage().get("token");

        final Claims claims = jwtProvider.getAccessClaims(access);
        final String email = claims.getSubject();
        if (authService.getRefreshStorage().containsKey(email)) {
            JwtResponse response = new JwtResponse(access,authService.getRefreshStorage().get(email));
         Map<String, String> nRefreshStorage = authService.getRefreshStorage();
                        nRefreshStorage.remove("token");

                        authService.setRefreshStorage(nRefreshStorage);  
            
            return ResponseEntity.ok(response);

        }
        String refresh = jwtProvider.generateOauthRefreshToken(email);
        authService.loginAuth2(email,refresh);
        JwtResponse resp = new JwtResponse(access,refresh);
         Map<String, String> newRefreshStorage = authService.getRefreshStorage();
                        newRefreshStorage.remove("token");

                        authService.setRefreshStorage(newRefreshStorage);         
        
        return ResponseEntity.ok(resp);


    }


}
