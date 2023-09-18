package org.finalproject.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.*;
import org.finalproject.exception.AlreadyExistException;
import org.finalproject.repository.FriendJpaRepository;
import org.finalproject.repository.NotificationRepository;
import org.finalproject.repository.PostJpaRepository;
import org.finalproject.repository.UserJpaRepository;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService extends GeneralService<User> {

    private final UserJpaRepository userRepository;

    public final PostJpaRepository postRepository;

    public final FriendJpaRepository friendRepository;

    public final NotificationRepository notificationRepository;





    public Optional<User> getByEmail(String email) {
        if (userRepository.getByEmail(email).isEmpty()) {
            throw new EntityNotFoundException();
        }

        return userRepository.getByEmail(email);


    }

    public List<User> getUserByPartOfName(String part) {
        String partDb = part.toUpperCase();
        if ( userRepository.findUserByPartOfName(partDb).isEmpty() ) {
            throw new EntityNotFoundException();
        }
        return userRepository.findUserByPartOfName(partDb);
    }

    public Optional<User> getByFullName(String fullName) {
        if (userRepository.getByFullName(fullName).isEmpty()) {
            throw new EntityNotFoundException();
        }
        return userRepository.getByFullName(fullName);
    }

    @Override
    public Optional<User> findById(Long id) {
        if (userRepository.findById(id).isEmpty()) {

            throw new EntityNotFoundException();
        }

        return userRepository.findById(id);
    }

    @Override
    public User getOne(Long id) {
        if ( userRepository.getOne(id) == null ) {

            throw new EntityNotFoundException();
        }

        return userRepository.getOne(id);
    }

    public void update(User user) {

        User userDb = userRepository.getOne(user.getId());
        userDb.setFullName(user.getFullName());
        userDb.setEmail(user.getEmail());
        userDb.setBirthDate(user.getBirthDate());
        userDb.setCity(user.getCity());
        userDb.setProfileBackgroundPicture(user.getProfileBackgroundPicture());
        userDb.setProfilePicture(user.getProfilePicture());
        userDb.setAbout(user.getAbout());
        userDb.setCountry(user.getCountry());
        userDb.setWorkPlace(user.getWorkPlace());
        userRepository.save(userDb);
    }

    public void addLikes(Long id, Post post) {

        if ( post == null ) {
            throw new EntityNotFoundException();
        }
        List<User> likes = post.getLikes();
        User newLike = userRepository.getOne(id);
        if ( newLike == null ) {
            throw new EntityNotFoundException();
        }
        if ( likes.contains( newLike ) ) {
            throw new AlreadyExistException("Already liked");
        }

        likes.add(newLike);
        List<Post> likedPosts = newLike.getLikedPosts();
        likedPosts.add(post);
        newLike.setLikedPosts(likedPosts);
        userRepository.save(newLike);
        post.setLikes(likes);
        postRepository.save(post);
    }

    public void addRepost(Long id, Post post) {

        Set<User> reposts = post.getRepostsUsers();

        User newRepost = userRepository.getOne(id);
        reposts.add(newRepost);
        Set<Post> repostedPosts = newRepost.getReposts();
        repostedPosts.add(post);
        newRepost.setReposts(repostedPosts);
        userRepository.save(newRepost);
        post.setRepostsUsers(reposts);

        postRepository.save(post);

    }


    public void deleteUserById(Long userId) {

            User user = userRepository.getOne(userId);
            List<Chat> chats = user.getChats();
            for (Chat chat : chats) {
                chat.getUsers().removeIf(el -> el.getId().equals(userId));
            }
            List<Friend> friends = user.getFriends();
            for (Friend friend : friends) {
               friendRepository.delete(friend);
            }
            List<Friend> users = user.getUsers();
            for (Friend friend : users) {
                friendRepository.delete(friend);
            }
        List<Post> posts = user.getPosts();
        for (Post post : posts) {
           post.setUser(null);

            User deletedUser;
           if (userRepository.getByFullName("Deleted User").isEmpty()) {

          deletedUser = new User();
         deletedUser.setFullName("Deleted User");
         deletedUser.setEmail("deleted@gmail.com");

            userRepository.save(deletedUser);
           } else {
               deletedUser = userRepository.getByFullName("Deleted User").get();
           }

         post.setUser(deletedUser);

        }
            List<Post> likedPosts = user.getLikedPosts();
            for (Post post : likedPosts) {
                post.getLikes().removeIf(el -> el.getId().equals(userId));
            }

            Set<Post> repostedPosts = user.getReposts();
            for (Post post : repostedPosts) {
                post.getReposts().removeIf(el -> el.getId().equals(userId));
            }

        List<Notification> notifications = user.getNotifications();
        for (Notification  notification : notifications ) {

            notification.getReceiver().removeIf(el -> el.getId().equals(userId));

        }

        List<Notification> sentNotifications = user.getSentNotifications();
        User deletedUser;
        for (Notification  notification : sentNotifications ) {
            if (userRepository.getByFullName("Deleted User").isEmpty()) {

                deletedUser = new User();
                deletedUser.setFullName("Deleted User");
                deletedUser.setEmail("deleted@gmail.com");

                userRepository.save(deletedUser);
            } else {
               deletedUser = userRepository.getByFullName("Deleted User").get();
            }
              notification.setSender(deletedUser);
        }
            userRepository.deleteById(userId);
        }



}