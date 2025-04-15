package za.ac.up.artifactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import za.ac.up.artifactup.entity.ScavengerHunt;
import za.ac.up.artifactup.service.ScavengerHuntService;

@Component
@RequiredArgsConstructor
public class ScavengerHuntQualifier {

    private final ScavengerHuntService<ScavengerHunt> scavengerHuntService;

    @Named("stringToScavengerHunt")
    public ScavengerHunt stringToScavengerHunt(String scavengerHuntName) {
        return scavengerHuntService.findByName(scavengerHuntName).orElseThrow(() -> new RuntimeException("Scavenger Hunt \"" + scavengerHuntName + "\" does not exist"));
    }
}
