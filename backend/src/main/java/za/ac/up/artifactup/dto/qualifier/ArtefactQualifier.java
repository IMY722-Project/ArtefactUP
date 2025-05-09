package za.ac.up.artifactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.config.BucketConfig;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.service.ArtefactService;
import za.ac.up.artifactup.service.BucketService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ArtefactQualifier {
  private final ArtefactService<Artefact> artefactService;
  private final BucketService bucketService;
  private final BucketConfig bucketConfig;

  @Named("stringToArtefact")
  public Artefact stringToArtefact(String artefactTitle) {
    return artefactService.findByTitle(artefactTitle).orElseThrow(
        () -> new RuntimeException("Artefact \"" + artefactTitle + "\" does not exist"));
  }

  @Named("fileToUrl")
  public String fileToUrl(MultipartFile imageFile) {
    try {
      return bucketService.putObjectIntoBucket(imageFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Named("mapToURL")
  public String FileNameToURL(String filename) {
    return "%s/%s/%s".formatted(bucketConfig.getAwsEndpoint(), bucketConfig.getS3BucketName(), filename);
  }
}
