package za.ac.up.artifactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.service.BucketService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ArtefactQualifier {

  private final BucketService bucketService;

  @Named("fileToUrl")
  public String fileToUrl(MultipartFile imageFile) {
    try {
      return bucketService.putObjectIntoBucket(imageFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
