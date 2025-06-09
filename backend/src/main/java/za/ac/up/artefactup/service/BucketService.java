package za.ac.up.artefactup.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface BucketService {

    /**
     * upload given file as objectName to S3 bucket
     *
     * @param file: image multipart file
     * @return String URL of the image on the s3 bucket
     */
    String putObjectIntoBucket(MultipartFile file) throws IOException;

    /**
     * download given objectName from S3 bucket
     *
     * @param objectName: name of the object in the s3 bucket
     * @return byte[] of the image
     */
    byte[] getObjectFromBucket(String objectName) throws IOException;
}
