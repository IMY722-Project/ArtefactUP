package za.ac.up.artefactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.entity.Museum;
import za.ac.up.artefactup.service.MuseumService;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuseumQualifier {

  private final MuseumService<Museum> museumService;

  @Named("stringToMuseum")
  public Museum stringToMuseum(String museumName) {
    return museumService.findByName(museumName)
        .orElseThrow(() -> new RuntimeException("Museum \"" + museumName + "\" does not exist"));
  }

}
