package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Notification;
import org.finalproject.entity.UserImage;
import org.finalproject.repository.UserImageRepository;
import org.finalproject.repository.UserJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserImageService extends GeneralService<UserImage> {

    private final UserImageRepository userImageRepository;

    private final UserJpaRepository userRepository;

    public Page<UserImage> findAuthUserImagesPageable(Long id, Pageable pageable) {

        return  userImageRepository.findAllByUser(userRepository.getOne(id),pageable);
    }
}