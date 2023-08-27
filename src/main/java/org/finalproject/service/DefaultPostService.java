package org.finalproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Post;
import org.finalproject.repository.PostJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPostService extends GeneralService<Post> {
    private final PostJpaRepository postRepository;

    public List<Post> findAllPosts() {
        return postRepository.findAllPosts();
    }

    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllPosts(pageable);
    }

}
