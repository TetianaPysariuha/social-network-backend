package org.finalproject.service;


import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Message;
import org.finalproject.entity.MessageImage;
import org.finalproject.repository.MessageImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMessageImageService extends GeneralService<MessageImage> {

    private final MessageImageRepository messageImageRepository;
    private final FileUpload fileUpload;

    public List<String> uploadImage(List<MultipartFile> multipartFiles, Long messageId) throws IOException {

        return fileUpload.uploadMessageFile(multipartFiles, messageId);
    }
}
