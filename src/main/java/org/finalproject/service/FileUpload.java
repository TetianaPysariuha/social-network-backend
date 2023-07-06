package org.finalproject.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUpload {

    private final Cloudinary cloudinary;

    public String uploadUserFile(MultipartFile multipartFile, Long userId) throws IOException {

        String folder = userId.toString().concat("/user images");
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of(folder, UUID.randomUUID().toString()))
                .get("url")
                .toString();

    }

    public String uploadPostFile(MultipartFile multipartFile, Long postId) throws IOException {

        String folder = postId.toString().concat("/post images");
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of(folder, UUID.randomUUID().toString()))
                .get("url")
                .toString();

    }

    public String uploadMessageFile(MultipartFile multipartFile, Long messageId) throws IOException {

        String folder = messageId.toString().concat("/message images");
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of(folder, UUID.randomUUID().toString()))
                .get("url")
                .toString();

    }


}
