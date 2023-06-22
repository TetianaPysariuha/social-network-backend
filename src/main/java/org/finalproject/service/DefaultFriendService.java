package org.finalproject.service;


import lombok.RequiredArgsConstructor;

import org.finalproject.entities.Friend;
import org.finalproject.repository.FriendJpaRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;
}
