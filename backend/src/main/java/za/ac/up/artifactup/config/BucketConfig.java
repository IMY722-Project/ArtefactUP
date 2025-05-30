package za.ac.up.artifactup.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;

import java.net.URI;

@Configuration
@Getter
public class BucketConfig {

  @Value("${aws.access.key}")
  String awsAccessKey;

  @Value("${aws.secret.key}")
  String awsSecretKey;

  @Value("${aws.endpoint}")
  String awsEndpoint;

  @Value("${aws.s3.bucket-name}")
  String s3BucketName;

  @Bean
  public S3Client getAmazonS3Client() {
    AwsCredentials credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

    return S3Client
        .builder()
        .endpointOverride(URI.create(awsEndpoint))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .region(Region.AF_SOUTH_1)
        .build();
  }

}
