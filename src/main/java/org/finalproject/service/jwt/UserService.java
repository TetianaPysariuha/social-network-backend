package org.finalproject.service.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.finalproject.entities.User;
import org.finalproject.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userRepository;

    public Optional<User> getByEmail(@NonNull String email) {

        return userRepository.getByEmail(email);
    }

}