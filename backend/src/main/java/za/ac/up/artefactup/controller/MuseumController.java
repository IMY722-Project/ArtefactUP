package za.ac.up.artefactup.controller;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.MuseumDTO;
import za.ac.up.artefactup.service.MuseumService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/museum")
@RequiredArgsConstructor
public class MuseumController {

  private final MuseumService<MuseumDTO> serviceFacade;

  @GetMapping
  public ResponseEntity<List<MuseumDTO>> findAllMuseums() {
    return ResponseEntity.ok(serviceFacade.findAll());
  }

  @PostMapping("/create")
  public ResponseEntity<MuseumDTO> createMuseum(@RequestBody MuseumDTO museumDTO) {
    return ResponseEntity.ok(serviceFacade.create(museumDTO));
  }

  @PutMapping("/update")
  public ResponseEntity<MuseumDTO> updateMuseum(@RequestBody MuseumDTO artefactDTO) {
    return ResponseEntity.ok(serviceFacade.update(artefactDTO));
  }
}
