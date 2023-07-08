package org.finalproject.service.jwt;




import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.finalproject.entity.User;
import org.finalproject.exception.AuthException;
import org.finalproject.jwt.JwtAuthentication;
import org.finalproject.jwt.JwtRequest;
import org.finalproject.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AuthService {
    @Autowired
    private  UserService userService;
    private  Map<String, String> refreshStorage = new HashMap<>();

    private  Map<String, HttpServletResponse> responseStorage = new HashMap<>();
    @Autowired
    private  JwtProvider jwtProvider;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByEmail(authRequest.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())){

            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(authRequest.getEmail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }
    public void loginAuth2(String email,String refreshToken) {


        refreshStorage.put(email, refreshToken);


    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();

            System.out.println(email);
            final String saveRefreshToken = refreshStorage.get(email);

            System.out.println(saveRefreshToken);
            System.out.println(saveRefreshToken.equals(refreshToken));
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getEmail(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("JWT token is invalid");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
