package za.ac.up.artefactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.entity.ScavengerHunt;
import za.ac.up.artefactup.service.ScavengerHuntService;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScavengerHuntQualifier {

    private final ScavengerHuntService<ScavengerHunt> scavengerHuntService;

    @Named("stringToScavengerHunt")
    public ScavengerHunt stringToScavengerHunt(String scavengerHuntName) {
        return scavengerHuntService.findByName(scavengerHuntName)
                .orElseThrow(() -> new RuntimeException("Scavenger Hunt \"" + scavengerHuntName + "\" does not exist"));
    }
}
