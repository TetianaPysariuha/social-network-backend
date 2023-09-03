package org.finalproject.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.exception.AlreadyExistException;
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

}