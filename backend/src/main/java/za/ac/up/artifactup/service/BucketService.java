package za.ac.up.artifactup.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BucketService {

  /**
   * upload given file as objectName to S3 bucket
   *
   * @param file: image multipart file
   * @return String URL of the image on the s3 bucket
   */
  String putObjectIntoBucket(MultipartFile file) throws IOException;
}
