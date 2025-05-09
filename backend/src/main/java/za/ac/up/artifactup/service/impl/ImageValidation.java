package za.ac.up.artifactup.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.BFMatcher;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.service.BucketService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageValidation {

    private final BucketService bucketService;

    public boolean validateImage(Artefact artefact, MultipartFile image) {

        nu.pattern.OpenCV.loadLocally();
        String s3Key = artefact.getImageUrl();
        byte[] refBytes;

        try {
            refBytes = getS3ImageBytes(s3Key);
        } catch (IOException e) {
            log.error("Could not get image from S3 bucket: {}", s3Key, e);
            return false;
        }
        byte[] userBytes = getImageBytes(image);

        Mat refMat = Imgcodecs.imdecode(new MatOfByte(refBytes), Imgcodecs.IMREAD_COLOR);
        Mat userMat = Imgcodecs.imdecode(new MatOfByte(userBytes), Imgcodecs.IMREAD_COLOR);
        if (refMat.empty() || userMat.empty()) {
            log.error("One of the images could not be decoded (empty Mat)");
            return false;
        }

        return imageMatching(refMat, userMat);
    }

    private byte[] getS3ImageBytes(String s3Key) throws IOException {
        return bucketService.getObjectFromBucket(s3Key);
    }

    private byte[] getImageBytes(MultipartFile image) {
        try {
            return image.getBytes();
        } catch (IOException ex) {
            log.warn("Uploaded image unreadable {}", image.getOriginalFilename(), ex);
            throw new IllegalArgumentException("Bad upload", ex);
        }
    }

    private boolean imageMatching(Mat refMat, Mat userMat) {

        ORB orb = ORB.create(1000);
        MatOfKeyPoint kp1 = new MatOfKeyPoint(), kp2 = new MatOfKeyPoint();
        Mat desc1 = new Mat(), desc2 = new Mat();
        orb.detectAndCompute(refMat, new Mat(), kp1, desc1);
        orb.detectAndCompute(userMat, new Mat(), kp2, desc2);

        if (desc1.empty() || desc2.empty()) {
            log.info("No descriptors found for artefact ");
            return false;
        }

        BFMatcher matcher = BFMatcher.create(Core.NORM_HAMMING, true);
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(desc1, desc2, matches);
        List<DMatch> list = matches.toList();
        if (list.isEmpty()) {
            log.info("No feature matches for artefact");
            return false;
        }

        list.sort(Comparator.comparingDouble(d -> d.distance));
        int keep = Math.min((int) (list.size() * 0.15), 50);
        List<DMatch> good = list.subList(0, keep);

        double avgDist = good.stream().mapToDouble(d -> d.distance).average().orElse(100);

        boolean matched = avgDist < 35.0;

        log.info("Artefact image match result: {}", matched);

        return matched;
    }

}
