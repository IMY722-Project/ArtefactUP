package za.ac.up.artifactup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.service.ArtefactService;

import java.util.List;

@RestController
@RequestMapping("/api/artefact")
@RequiredArgsConstructor
public class ArtefactController {

  private final ArtefactService<ArtefactDTO> serviceFacade;

  @GetMapping
  public ResponseEntity<List<ArtefactDTO>> findAllArtifacts() {
    return ResponseEntity.ok(serviceFacade.findAll());
  }

  @PostMapping("/create")
  public ResponseEntity<ArtefactDTO> createArtifact(@RequestBody ArtefactDTO artefactDTO) {
    return ResponseEntity.ok(serviceFacade.create(artefactDTO));
  }

  @PutMapping("/update")
  public ResponseEntity<ArtefactDTO> updateArtifact(@RequestBody ArtefactDTO artefactDTO) {
    return ResponseEntity.ok(serviceFacade.create(artefactDTO));
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    serviceFacade.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/collection/{collectionName}")
  public ResponseEntity<List<ArtefactDTO>> findAllArtifactsByCollectionName(
      @PathVariable String collectionName) {
    return ResponseEntity.ok(serviceFacade.findAllArtifactsByCollectionName(collectionName));
  }

  @GetMapping("/museum/{museumName}")
  public ResponseEntity<List<ArtefactDTO>> findAllArtifactsByMuseumName(
      @PathVariable String museumName) {
    return ResponseEntity.ok(serviceFacade.findAllArtifactsByMuseumName(museumName));
  }

}
