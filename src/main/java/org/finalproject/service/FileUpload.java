package org.finalproject.service;

import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
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

    public List<String> uploadUseListOfrFiles(List<MultipartFile> multipartFiles, Long userId) throws IOException {

        String folder = userId.toString().concat("/user images");
        List<String> filesUrl = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            filesUrl.add(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of(folder, UUID.randomUUID().toString()))
                    .get("url")
                    .toString());
        }
        return filesUrl;
    }

    public List<String> uploadPostFile(List<MultipartFile> multipartFiles, Long postId) throws IOException {

        String folder = postId.toString().concat("/post images");
        List<String> filesUrl = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            filesUrl.add(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of(folder, UUID.randomUUID().toString()))
                    .get("url")
                    .toString());
        }
        return filesUrl;

    }

    public List<String> uploadMessageFile(List<MultipartFile> multipartFiles, Long messageId) throws IOException {

        String folder = messageId.toString().concat("/message images");
        List<String> filesUrl = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            filesUrl.add(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of(folder, UUID.randomUUID().toString()))
                    .get("url")
                    .toString());
        }
        return filesUrl;
    }


}
