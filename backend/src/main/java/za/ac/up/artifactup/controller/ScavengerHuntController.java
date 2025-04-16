package za.ac.up.artifactup.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.up.artifactup.dto.ScavengerHuntDTO;
import za.ac.up.artifactup.service.ScavengerHuntService;

@RestController
@RequestMapping("/api/hunts")
@RequiredArgsConstructor
public class ScavengerHuntController {

    private final ScavengerHuntService<ScavengerHuntDTO> scavengerHuntServiceFacade;

    @PostMapping("/create")
    public ResponseEntity<ScavengerHuntDTO> createHunt(@RequestBody ScavengerHuntDTO scavengerHuntDTO) {
        return ResponseEntity.ok(scavengerHuntServiceFacade.create(scavengerHuntDTO));
    }

    @PostMapping("/{huntId}/delete")
    public ResponseEntity<Void> deleteHunt(@PathVariable Long huntId) {
        scavengerHuntServiceFacade.deleteById(huntId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScavengerHuntDTO>> getAllHunts() {
        return ResponseEntity.ok(scavengerHuntServiceFacade.findAll());
    }

}