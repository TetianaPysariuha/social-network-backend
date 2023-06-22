package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entities.User;
import org.finalproject.repository.UserJpaRepository;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService extends GeneralService<User> {

    private final UserJpaRepository userRepository;
}