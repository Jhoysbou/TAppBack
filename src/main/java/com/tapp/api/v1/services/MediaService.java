package com.tapp.api.v1.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.tapp.api.v1.utils.AwsCredentials;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MediaService {
    private static final AWSCredentials CREDENTIALS = new BasicAWSCredentials(AwsCredentials.ACCESS_KEY, AwsCredentials.SECRET_ACCESS_KEY);
    private static final String BUCKET_NAME = "tapp-media";
    private static final String TEST_IMAGES_PATH = "test_images/";
    private static AmazonS3 s3Client;

    public String uploadTestImage(final MultipartFile file) {
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS))
                .withRegion(Regions.EU_NORTH_1)
                .build();
        try {
            s3Client.putObject(BUCKET_NAME, TEST_IMAGES_PATH + file.getOriginalFilename(),
                    new ByteArrayInputStream(file.getBytes()), new ObjectMetadata());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://tapp-media.s3.eu-north-1.amazonaws.com/test_images/" + file.getOriginalFilename();
    }
}
