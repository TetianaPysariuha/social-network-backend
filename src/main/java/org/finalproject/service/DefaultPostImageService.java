package org.finalproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.PostImage;
import org.finalproject.repository.PostImageRepository;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPostImageService extends GeneralService<PostImage> {
    private final PostImageRepository postImageRepository;

}
