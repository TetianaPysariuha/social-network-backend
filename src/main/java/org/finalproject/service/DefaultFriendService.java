package org.finalproject.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.friend.*;
import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.exception.AuthException;
import org.finalproject.repository.FriendJpaRepository;
import org.finalproject.service.jwt.UserService;
import org.hibernate.Session;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.util.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultFriendService extends GeneralService<Friend> {
    private final FriendJpaRepository friendRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserService userService;
    @Autowired
    private FriendDtoMapper friendDtoMapper;
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

    public List<Friend> friendsOfUser() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            return friendsOfUser(user.get().getId());
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<Friend> friendsOfUser(Pageable pageable) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<Friend> friends = friendRepository.friendsOfUser(user.get().getId(), pageable);
            org.hibernate.Session session = (Session) entityManager.getDelegate();
            return friends.stream().map((el) -> {
                session.evict(el);
                if (Objects.equals(el.getFriend().getId(), user.get().getId())) {
                    User tmp = el.getFriend();
                    el.setFriend(el.getUser());
                    el.setUser(tmp);
                }
                return el;
            }).toList();
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<User> suggestedUsersForFriendship() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            return friendRepository.suggestedUsersForFriendship(user.get().getId());
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<User> suggestedUsersForFriendship(Pageable pageable) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            return friendRepository.suggestedUsersForFriendship(user.get().getId(), pageable);
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<Friend> friendshipRequests() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            return friendRepository.friendRequests(user.get().getId()).stream()
                    .filter(el -> Objects.equals(el.getStatus(), FriendStatus.pending))
                    .toList();
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<Friend> friendshipRequests(Pageable pageable) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            return friendRepository.friendRequests(user.get().getId(), pageable).stream()
                    .filter(el -> Objects.equals(el.getStatus(), FriendStatus.pending))
                    .toList();
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<User> getMutualFriends(Long friendId) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            return friendRepository.getMutualFriends(user.get().getId(), friendId);
        } else {
            throw new AuthException("User is not authorised");
        }
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

    public Friend saveNewById(FriendRequestCreateDto friendRequestCreateDto) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<Friend> friends = friendRepository.getFriendByBothID(user.get().getId(), friendRequestCreateDto.getFriendID());
            List<Friend> filteredFriends = friends.stream().filter(el -> (el.getStatus() == FriendStatus.pending || el.getStatus() == FriendStatus.removed
                    || el.getStatus() == FriendStatus.accepted)).toList();
            if (filteredFriends.size() > 0) {
                return filteredFriends.get(0);
            } else {
                return super.save(new Friend(
                        friendRequestCreateDto.getStatus() != null
                                ? FriendStatus.forValue(friendRequestCreateDto.getStatus())
                                : FriendStatus.pending,
                        userJpaRepository.findEntityById(user.get().getId()),
                        userJpaRepository.findEntityById(friendRequestCreateDto.getFriendID())
                ));
            }
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public Friend changeStatus(Long id, String status) {
        Friend friend = friendRepository.findEntityById(id);
        if (friend != null) {
            friend.setStatus(FriendStatus.forValue(status));
            return super.save(friend);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<Friend> getFriendByName(String name, Pageable pageable) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<Friend> friends = friendRepository.getFriendByUserIdFriendName(user.get().getId(), name, pageable);
            org.hibernate.Session session = (Session) entityManager.getDelegate();
            return friends.stream().map((el) -> {
                session.evict(el);
                if (Objects.equals(el.getFriend().getId(), user.get().getId())) {
                    User tmp = el.getFriend();
                    el.setFriend(el.getUser());
                    el.setUser(tmp);
                }
                return el;
            }).toList();
        } else {
            throw new AuthException("User is not authorised");
        }
    }

    public List<List<FriendDto>> getBirthdays() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<Friend> friend = friendsOfUser();
            List<List<FriendDto>> birthdays = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                int finalI = (i + (new Date()).getMonth()) % 12;
                birthdays.add(friend.stream()
                        .filter(fr -> fr.getFriend().getBirthDate() != null && fr.getFriend().getBirthDate().getMonth() == finalI
                                && fr.getStatus() == FriendStatus.accepted)
                        .map(friendDtoMapper::convertToDto).toList());
            }
            return birthdays;
        } else {
            throw new AuthException("User is not authorised");
        }
    }
}
