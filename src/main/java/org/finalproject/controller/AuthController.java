package org.finalproject.controller;



import lombok.RequiredArgsConstructor;
import org.finalproject.dto.UserRequestDto;
import org.finalproject.entity.User;
import org.finalproject.jwt.*;
import org.finalproject.service.GeneralService;
import org.finalproject.service.jwt.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    GeneralService<User> userService;

    @Autowired

    private JavaMailSender javaMailSender;


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
    @PostMapping("/passwordLetter")
    public ResponseEntity <?> sendChangePasswordMessage(@RequestBody Email email) {
        Optional   <User> userOptional = userService.findAll().stream().filter(el -> el.getEmail().equals(email.getEmail())).findAny();
        if(userOptional.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getEmail());
        simpleMailMessage.setSubject("Go to this page and use this code to restore your password");
        simpleMailMessage.setText("http://localhost:5173/password" + " " + "Code:"  + userOptional.get().getActivationCode());

        javaMailSender.send(simpleMailMessage);

        return ResponseEntity.ok().body("Ok");

    }
    @PutMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
         Optional   <User> userOptional = userService.findAll().stream().filter(el -> el.getActivationCode().equals(changePasswordRequest.getCode())).findAny();
          if(userOptional.isEmpty()){
              return ResponseEntity.badRequest().body("User not found");
          }
          User user = userOptional.get();
          String password = changePasswordRequest.getNewPassword();
          user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
