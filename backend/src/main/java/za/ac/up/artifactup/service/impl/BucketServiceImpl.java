package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import za.ac.up.artifactup.service.BucketService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

  private final S3Client s3Client;

  @Value("${aws.endpoint}")
  private String awsEndpoint;

  @Override
  public String putObjectIntoBucket(MultipartFile file) throws IOException {
    PutObjectRequest request = PutObjectRequest.builder()
        .bucket("museum-artefacts")
        .key(file.getOriginalFilename())
        .contentType(file.getContentType())
        .build();

    s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
    return file.getOriginalFilename();
  }
}
