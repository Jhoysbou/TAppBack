package com.tapp.api.v1.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.tapp.api.v1.utils.Credentials;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MediaService {
    private static final AWSCredentials CREDENTIALS = new BasicAWSCredentials(Credentials.AWS_ACCESS_KEY, Credentials.AWS_SECRET_ACCESS_KEY);
    private static final String BUCKET_NAME = "tapp-media";
    private static final String TEST_IMAGES_PATH = "test_images/";
    private static final String STICKER_IMAGES_PATH = "sticker_images/";
    private static AmazonS3 s3Client;

    public MediaService() {
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    @Async
    public CompletableFuture<String> uploadTestImage(final MultipartFile file) {
        try {
            s3Client.putObject(BUCKET_NAME, TEST_IMAGES_PATH + file.getOriginalFilename(),
                    new ByteArrayInputStream(file.getBytes()), new ObjectMetadata());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("https://tapp-media.s3.eu-north-1.amazonaws.com/test_images/" + file.getOriginalFilename());
    }

    public CompletableFuture<String> uploadStickerImage(final MultipartFile file) {
        try {
            s3Client.putObject(BUCKET_NAME, STICKER_IMAGES_PATH + file.getOriginalFilename(),
                    new ByteArrayInputStream(file.getBytes()), new ObjectMetadata());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/" + file.getOriginalFilename());
    }
}
