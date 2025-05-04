package za.ac.up.artifactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import za.ac.up.artifactup.entity.Museum;
import za.ac.up.artifactup.service.MuseumService;

@Component
@RequiredArgsConstructor
public class MuseumQualifier {

  private final MuseumService<Museum> museumService;

  @Named("stringToMuseum")
  public Museum stringToMuseum(String museumName) {
    return museumService.findByName(museumName).orElseThrow(() -> new RuntimeException("Museum \"" + museumName + "\" does not exist"));
  }

}
