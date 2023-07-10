package com.nasigolang.ddbnb.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class FileUploadUtils {

    private final Logger log = LoggerFactory.getLogger(FileUploadUtils.class);

    private String S3Bucket = "ddbnb-images"; // Bucket 이름

    @Autowired
    AmazonS3Client amazonS3Client;

    public String saveFile(MultipartFile multipartFile) throws IOException {

        String imageName = UUID.randomUUID().toString().replace("-", "");

        String fileName = imageName + "." + FilenameUtils.getExtension(multipartFile.getResource().getFilename());

        long size = multipartFile.getSize(); // 파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);

        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, fileName, multipartFile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imagePath = amazonS3Client.getUrl(S3Bucket, fileName).toString(); // 접근가능한 URL 가져오기

        return imagePath;
    }

    public String saveFile(byte[] fileBytes) throws IOException {

        String imageName = UUID.randomUUID().toString().replace("-", "");

        String fileName = imageName + ".png";  // 파일 확장자는 예시로 png로 설정하였습니다.

        InputStream inputStream = new ByteArrayInputStream(fileBytes);

        long size = fileBytes.length; // 파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType("multipart/form-data");
        objectMetaData.setContentLength(size);

        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, fileName, inputStream, objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imagePath = amazonS3Client.getUrl(S3Bucket, fileName).toString(); // 접근가능한 URL 가져오기


        return imagePath;
    }

    public void deleteFile(String fileUrl) {

        String fileName = fileUrl.replace("https://ddbnb-images.s3.ap-northeast-2.amazonaws.com/", "");

        amazonS3Client.deleteObject(new DeleteObjectRequest(S3Bucket, fileName));
    }
}
