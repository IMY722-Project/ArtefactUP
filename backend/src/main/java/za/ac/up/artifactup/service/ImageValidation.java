package za.ac.up.artifactup.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
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
import za.ac.up.artifactup.entity.FileStorage;
import za.ac.up.artifactup.entity.FileType;
import za.ac.up.artifactup.repository.FileStorageRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageValidation {

    private final FileStorageRepository fileStorageRepository;
    private final AmazonS3 amazonS3;
    private final String bucketName = "museum-artefacts";

    public boolean validateImage(Artefact artefact, MultipartFile image) {

        FileStorage ref = fileStorageRepository
                .findFirstByArtefactAndFileType(artefact, FileType.IMAGE)
                .orElseThrow(() -> {
                    log.error("No reference image for artefact {}", artefact.getId());
                    return new IllegalStateException("Reference image missing");
                });

        String s3Key = ref.getS3Url();

        byte[] refBytes = getS3ImageBytes(s3Key);
        byte[] userBytes = getImageBytes(image);

        Mat refMat = Imgcodecs.imdecode(new MatOfByte(refBytes), Imgcodecs.IMREAD_COLOR);
        Mat userMat = Imgcodecs.imdecode(new MatOfByte(userBytes), Imgcodecs.IMREAD_COLOR);
        if (refMat.empty() || userMat.empty()) {
            log.error("One of the images could not be decoded (empty Mat)");
            return false;
        }

        return imageMatching(refMat, userMat);
    }

    private byte[] getS3ImageBytes(String s3Key) {
        try (S3Object obj = amazonS3.getObject(bucketName, s3Key)) {
            return obj.getObjectContent().readAllBytes();
        } catch (IOException ex) {
            log.error("S3 download failed for {}", s3Key, ex);
            throw new RuntimeException("Cannot download reference image", ex);
        }
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

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
