package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entity.User;
import org.finalproject.repository.UserJpaRepository;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService extends GeneralService<User> {

    private final UserJpaRepository userRepository;


    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public Optional<User> getByFullName(String fullName) {
        return userRepository.getByFullName(fullName);
    }
}