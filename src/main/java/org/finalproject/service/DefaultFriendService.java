package org.finalproject.service;


import lombok.RequiredArgsConstructor;

import org.finalproject.entity.Friend;
import org.finalproject.repository.FriendJpaRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;
}
