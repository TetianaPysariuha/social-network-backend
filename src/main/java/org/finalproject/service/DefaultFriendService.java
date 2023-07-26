package org.finalproject.service;


import lombok.RequiredArgsConstructor;

import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.repository.FriendJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;


    public List<Friend> friendsOfUser(Long userId) {
        List<Friend> friends = friendRepository.findFriends(userId);
        return friends.stream().map((el) -> {
            if (Objects.equals(el.getFriend().getId(), userId)) {
                User tmp = el.getFriend();
                el.setFriend(el.getUser());
                el.setUser(tmp);
            }
            return el;
        }).toList();
    }
}
