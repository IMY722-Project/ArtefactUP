package za.ac.up.artifactup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.dto.StepValidationResultDTO;
import za.ac.up.artifactup.service.impl.ScavengerHuntStepFacade;

@RestController
@RequestMapping("/api/hunts/steps")
@RequiredArgsConstructor
public class ScavengerHuntStepController {

    private final ScavengerHuntStepFacade scavengerHuntStepFacade;

    @PostMapping("/{huntId}/validate")
    public ResponseEntity<StepValidationResultDTO> validateHuntStep(@PathVariable Long huntId, @RequestParam("imageFile") MultipartFile image, @RequestHeader("Session-id") String sessionId) {
        return ResponseEntity.ok(scavengerHuntStepFacade.validateStep(sessionId, huntId, image));
    }

}
