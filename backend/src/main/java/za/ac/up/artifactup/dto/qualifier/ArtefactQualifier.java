package za.ac.up.artifactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.service.ArtefactService;

@Component
@RequiredArgsConstructor
public class ArtefactQualifier {

    private final ArtefactService<Artefact> artefactService;

    @Named("stringToArtefact")
    public Artefact stringToArtefact(String artefactName) {
        return artefactService.findByName(artefactName).orElseThrow(() -> new RuntimeException("Artefact \"" + artefactName + "\" does not exist"));
    }
}
