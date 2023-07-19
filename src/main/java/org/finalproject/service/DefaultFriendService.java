package org.finalproject.service;


import lombok.RequiredArgsConstructor;

import org.finalproject.entity.Friend;
import org.finalproject.repository.FriendJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;


   public List<Friend> findFriends(@Param("id")  Long id ) {
       return friendRepository.findFriends(id);
    }
}
