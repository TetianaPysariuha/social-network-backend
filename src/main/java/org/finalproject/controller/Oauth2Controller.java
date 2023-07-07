package org.finalproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.finalproject.entity.User;
import org.finalproject.jwt.JwtResponse;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.service.jwt.AuthService;
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


    @GetMapping("oauth2/search")
    Optional<User> search(@RequestParam String email, OAuth2Authentication authentication) {

        String role = authentication.getAuthorities().iterator().next().getAuthority();

        return repository.getByEmail(email);
    }

    @GetMapping("/oauth2/authorization/google")
  public ResponseEntity <?>  login( ) throws IOException {

        String auth = SecurityContextHolder.getContext().getAuthentication() .getPrincipal().toString();
        System.out.println(auth);

    String access = authService.getRefreshStorage().get("token");
    String refresh = authService.getRefreshStorage().get("refresh");
    final JwtResponse token =new JwtResponse(access,refresh);
    return ResponseEntity.ok(token);
//}

    }


}
