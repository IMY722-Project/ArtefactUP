package za.ac.up.artifactup.dto;

import lombok.Data;
import za.ac.up.artifactup.entity.FileType;

@Data
public class FileStorageDTO {
    private Long id;
    private Long artefactId;  // Only storing the ID instead of full entity
    private FileType fileType;
    private String s3Url;
}
