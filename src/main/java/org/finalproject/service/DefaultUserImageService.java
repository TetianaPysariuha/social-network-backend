package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.UserImage;
import org.finalproject.repository.UserImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserImageService extends GeneralService<UserImage> {

    private final UserImageRepository userImageRepository;
}