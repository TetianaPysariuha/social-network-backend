package org.finalproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Post;
import org.finalproject.repository.PostJpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPostService extends GeneralService<Post> {
    private final PostJpaRepository userRepository;
}
