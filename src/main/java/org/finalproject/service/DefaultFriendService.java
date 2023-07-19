package org.finalproject.service;


import lombok.RequiredArgsConstructor;

import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.repository.FriendJpaRepository;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.utilites.FriendStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;
    private final UserJpaRepository userJpaRepository;

    public List<Friend> friendsOfUser(Long userId) {
        List<Friend> friends = friendRepository.friendsOfUser(userId);
        friends.stream().map((el) -> {
            if(Objects.equals(el.getFriend().getId(), userId)) {
                User tmp = el.getFriend();
                el.setFriend(el.getUser());
                el.setUser(tmp);
            };
            return el;
        });
        return friends;
    }

    public List<User> suggestedUsersForFriendship(Long userId){
        return friendRepository.suggestedUsersForFriendship(userId);
    }

    public List<User> getMutualFriends(Long userId, Long friendId){
        return friendRepository.getMutualFriends(userId, friendId);
    }

    @Override
    public Friend save(Friend entity) {
//        List<Friend> friends = friendsOfUser(entity.getUser().getId());
//        List<Friend> filteredFriends = friends.stream().filter(el -> Objects.equals(el.getUser().getId(), entity.getUser().getId())
//                && Objects.equals(el.getFriend().getId(), entity.getFriend().getId())).toList();
//        if(filteredFriends.size()>0){
//            return filteredFriends.get(0);
//        } else{
//            return super.save(entity);
//        }
        System.out.println(entity);
        if(friendRepository.existsById(entity.getId())){
            return super.save(entity);
        } else {
            return null;
        }
    }

    public Friend saveNewById(Long userId, Long friendId) {
        List<Friend> friends = friendsOfUser(userId);
        List<Friend> filteredFriends = friends.stream().filter(el -> Objects.equals(el.getUser().getId(), userId)
                && Objects.equals(el.getFriend().getId(), friendId)
                && (el.getStatus() == FriendStatus.pending
                || el.getStatus() == FriendStatus.accepted)).toList() ;
        if(filteredFriends.size()>0){
            return filteredFriends.get(0);
        } else{
            return super.save(new Friend(FriendStatus.pending,
                                            userJpaRepository.findEntityById(userId),
                                            userJpaRepository.findEntityById(friendId)));
        }
    }

    public Friend update(Long id, FriendStatus friendStatus, Long userId, Long friendId) {
        Friend friend = getOne(id);
        if(friend.getUser().getId() != userId) {
            friend.setUser(userJpaRepository.findEntityById(userId));
        }
        if(friend.getFriend().getId() != friendId) {
            friend.setFriend(userJpaRepository.findEntityById(friendId));
        }
        if(friend.getStatus() != friendStatus) {
            friend.setStatus(friendStatus);
        }
        System.out.println(friend);
        return super.save(friend);
    }
}
