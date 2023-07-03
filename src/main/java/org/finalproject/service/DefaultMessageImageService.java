package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Message;
import org.finalproject.entity.MessageImage;
import org.finalproject.repository.MessageImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMessageImageService extends GeneralService<MessageImage> {

    private MessageImageRepository messageImageRepository;
}
