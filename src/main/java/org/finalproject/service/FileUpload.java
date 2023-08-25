package org.finalproject.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
public class FileUpload {

    private final Cloudinary cloudinary;

    public String uploadUserFile(MultipartFile multipartFile, Long userId) throws IOException {

        String folder = "/user_images/".concat(userId.toString());
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        ObjectUtils.asMap("folder", folder))
                .get("url")
                .toString();

    }

    public List<String> uploadUseListOfrFiles(List<MultipartFile> multipartFiles, Long userId) throws IOException {

        String folder = "/user_images/".concat(userId.toString());
        List<String> filesUrl = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            filesUrl.add(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            ObjectUtils.asMap("folder", folder))
                    .get("url")
                    .toString());
        }
        return filesUrl;
    }

    public List<String> uploadPostFile(List<MultipartFile> multipartFiles, Long postId) throws IOException {

        String folder = "/post_images/".concat(postId.toString());
        List<String> filesUrl = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            filesUrl.add(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            ObjectUtils.asMap("folder", folder))
                    .get("url")
                    .toString());
        }
        return filesUrl;

    }

    public List<String> uploadMessageFile(List<MultipartFile> multipartFiles, Long messageId) throws IOException {

        String folder = "/message_images/".concat(messageId.toString());
        List<String> filesUrl = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            filesUrl.add(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            ObjectUtils.asMap("folder", folder))
                    .get("url")
                    .toString());
        }
        return filesUrl;
    }


}
