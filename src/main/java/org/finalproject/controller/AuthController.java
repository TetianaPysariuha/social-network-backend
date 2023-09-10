package org.finalproject.controller;



import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.finalproject.dto.UserRequestDto;
import org.finalproject.entity.User;
import org.finalproject.jwt.*;
import org.finalproject.service.GeneralService;
import org.finalproject.service.jwt.AuthService;
import org.finalproject.service.jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Getter
@Setter
public class AuthController {

    private final AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GeneralService<User> userService;

    @Autowired

    private UserService service;

    private String url = "http://localhost:5173";

    @Autowired

    private JavaMailSender javaMailSender;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) {
        try {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Incorrect login or password");
        }
    }

@PostMapping("renew")

public ResponseEntity<?> returnRefresh(@RequestParam String refresh) {


    try {
     String token =   authService.returnRefresh(refresh);
        return ResponseEntity.ok(token);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    @PostMapping("registration")
    public ResponseEntity<?> register(@RequestBody RegisterRequest authRequest) {
        Optional<User> existingUser = service.getByEmail(authRequest.getEmail());
        if ( existingUser.isPresent() ) {
         return ResponseEntity.badRequest().body("User with that email already exists");
        }
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
        return "Activated Return to homepage ";

    }

    @PostMapping
    public ResponseEntity<?> getUrl(@RequestBody Email url) {
       setUrl(url.getEmail());

        return  ResponseEntity.ok(url);

    }


    @PostMapping("/passwordLetter")
    public ResponseEntity<?> sendChangePasswordMessage(@RequestBody Email email) {
        Optional<User> userOptional =  userService.findAll().stream().filter(el -> el.getEmail().equals(email.getEmail())).findAny();
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        authService.sendChangePasswordMessage(userOptional,email);

        return ResponseEntity.ok().body("Ok");

    }

    @PutMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {

         Optional<User> userOptional = userService.findAll().stream().filter(el -> el.getActivationCode().equals(changePasswordRequest.getCode())).findAny();
          if (userOptional.isEmpty()) {
              throw new EntityNotFoundException();
          }

          User user = userOptional.get();
          String password = changePasswordRequest.getNewPassword();
          user.setPassword(passwordEncoder.encode(password));
          if(user.isActivated() == false){
              user.setActivated(true);
          }
            userService.save(user);
            return ResponseEntity.ok().build();


    }
}
