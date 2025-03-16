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

  private final ArtefactService<ArtefactDTO> artefactService;

  @GetMapping
  public ResponseEntity<List<ArtefactDTO>> findAllArtifacts() {
    return ResponseEntity.ok(artefactService.findAll());
  }

  @PostMapping
  public ResponseEntity<ArtefactDTO> createArtifact(@RequestBody ArtefactDTO artefactDTO) {
    return ResponseEntity.ok(artefactService.saveArtefact(artefactDTO));
  }

  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<ArtefactDTO>> findAllArtifactsByCategoryId(@PathVariable Long categoryId) {
    return ResponseEntity.ok(artefactService.findAllArtifactsByCategoryId(categoryId));
  }

}
