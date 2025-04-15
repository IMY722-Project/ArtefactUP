package za.ac.up.artifactup.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArtefactDTO {
    private Long id;
    private String title;
    private String creator;
    private String description;
    private String museumName;
    private String dateCreated;
    private String locationCreated;
    private String physicalDimensions;
    private String type;
    private String rights;
    private String medium;
    private MultipartFile imageFile;
}
