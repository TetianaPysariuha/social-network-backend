package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.ImgComment;
import org.finalproject.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCommentService extends GeneralService<ImgComment> {

    private final CommentRepository commentRepository;
}