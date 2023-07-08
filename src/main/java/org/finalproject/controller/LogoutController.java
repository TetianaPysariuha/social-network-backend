package org.finalproject.controller;


import lombok.RequiredArgsConstructor;

import org.finalproject.service.jwt.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class LogoutController {
    private final AuthService authService;

    @GetMapping("logout")
    public void logout() {
      //  SecurityContextHolder.getContext().setAuthentication(null);

    }
}