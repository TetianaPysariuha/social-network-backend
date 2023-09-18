package org.finalproject.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.repository.FriendJpaRepository;
import org.hibernate.Session;
import org.springframework.data.repository.query.Param;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.utilites.FriendStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;
    private final UserJpaRepository userJpaRepository;
    @PersistenceContext
    EntityManager entityManager;

    public List<Friend> friendsOfUser(Long userId) {
        List<Friend> friends = friendRepository.friendsOfUser(userId);
        org.hibernate.Session session = (Session) entityManager.getDelegate();
        return friends.stream().map((el) -> {
            session.evict(el);
            if (Objects.equals(el.getFriend().getId(), userId)) {
                User tmp = el.getFriend();
                el.setFriend(el.getUser());
                el.setUser(tmp);
            }
            return el;
        }).toList();
    }

    //friendRepository.suggestedUsersForFriendship(userId); -> oll
    //friendRepository.FriendsForFriendship(userId); -> Friends
    //friendRepository.searchFriendsForCity(userId); -> City

    public List<User> suggestedUsersForFriendship(Long userId) {
        List<User> CitiAndAll = new ArrayList<>();
        List<User> FriendAndCiti = new ArrayList<>();
        List<User> FriendAndCitiAndAll = new ArrayList<>();
        HashSet<User> searchFriends = new HashSet<>();
        List<User> searchFriendsLi = new ArrayList<>();
        List<Friend> SizeFrend = friendRepository.findFriends(userId);
        if (SizeFrend.isEmpty()) {
            HashSet<User> searchFriendsForCity = friendRepository.searchFriendsForCity(userId);
            if (searchFriendsForCity.isEmpty()) {
                return friendRepository.suggestedUsersForFriendship(userId);
            } else
                searchFriendsLi = friendRepository.suggestedUsersForFriendship(userId);
            searchFriendsForCity.addAll(searchFriendsLi);
            CitiAndAll.addAll(searchFriendsForCity);
            return CitiAndAll;
        }else{
            searchFriends = friendRepository.FriendsForFriendship(userId);
            HashSet<User> searchFriendsForCity = friendRepository.searchFriendsForCity(userId);
            searchFriends.addAll(searchFriendsForCity);
            if (searchFriends.size() > 99){
                FriendAndCiti.addAll(searchFriends);
                return FriendAndCiti;
            }else {
                searchFriendsLi = friendRepository.suggestedUsersForFriendship(userId);
                searchFriends.addAll(searchFriendsLi);
                FriendAndCitiAndAll.addAll(searchFriends);
                return FriendAndCitiAndAll;
            }
        }
    }

    public List<Friend> friendshipRequests(Long userId) {
        return friendRepository.friendsOfUser(userId).stream()
                                .filter(el -> Objects.equals(el.getStatus(), FriendStatus.pending))
                                .toList();
    }

    public List<User> getMutualFriends(Long userId, Long friendId) {
        return friendRepository.getMutualFriends(userId, friendId);
    }

    public Friend update(Friend entity) {
        Friend friend = friendRepository.findEntityById(entity.getId());
        if (friend != null) {
            friend.setStatus(entity.getStatus());
            return super.save(friend);
        } else {
            return null;
        }
    }

    public Friend update(Long id, FriendStatus friendStatus, Long userId, Long friendId) {
        Friend friend = getOne(id);
        if (!Objects.equals(friend.getUser().getId(), userId)) {
            friend.setUser(userJpaRepository.findEntityById(userId));
        }
        if (!Objects.equals(friend.getFriend().getId(), friendId)) {
            friend.setFriend(userJpaRepository.findEntityById(friendId));
        }
        if (friend.getStatus() != friendStatus) {
            friend.setStatus(friendStatus);
        }
        return super.save(friend);
    }

    public Friend saveNewById(Long userId, Long friendId) {
        List<Friend> friends = friendRepository.getFriendByBothID(userId, friendId);
        List<Friend> filteredFriends = friends.stream().filter(el -> (el.getStatus() == FriendStatus.pending
                || el.getStatus() == FriendStatus.accepted)).toList() ;
        if (filteredFriends.size() > 0) {
            return filteredFriends.get(0);
        } else {
            return super.save(new Friend(FriendStatus.pending,
                                            userJpaRepository.findEntityById(userId),
                                            userJpaRepository.findEntityById(friendId)));
        }
    }
}
