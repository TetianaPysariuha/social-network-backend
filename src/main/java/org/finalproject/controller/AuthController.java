package org.finalproject.controller;



import lombok.RequiredArgsConstructor;
import org.finalproject.entity.User;
import org.finalproject.jwt.JwtRequest;
import org.finalproject.jwt.JwtResponse;
import org.finalproject.jwt.RefreshJwtRequest;
import org.finalproject.jwt.RegisterRequest;
import org.finalproject.service.GeneralService;
import org.finalproject.service.jwt.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {

    private final AuthService authService;

    @Autowired
    GeneralService<User> userService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("registration")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest authRequest) {
        authService.register(authRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }


    @GetMapping("/activate/{code}")
    public String activate(@PathVariable ("code") String code) {
        Optional<User> userOptional = userService.findAll().stream().filter(el->el.getActivationCode().equals(code)).findAny();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActivated(true);
            userService.save(user);
        }
        return "Activated Return to homepage http://localhost:5173";

    }
}
