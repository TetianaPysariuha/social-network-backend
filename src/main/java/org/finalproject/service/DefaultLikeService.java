package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Like;
import org.finalproject.entity.User;
import org.finalproject.repository.LikeJpaRepository;
import org.finalproject.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultLikeService extends GeneralService<Like>  {
    private final LikeJpaRepository likeRepository;
}
