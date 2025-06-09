package za.ac.up.artefactup.controller;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artefactup.dto.StepValidationResultDTO;
import za.ac.up.artefactup.service.ScavengerHuntStepService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hunts/steps")
@RequiredArgsConstructor
public class ScavengerHuntStepController {

    private final ScavengerHuntStepService<ScavengerHuntStepDTO> scavengerHuntStepFacade;

    @PostMapping(value = "/{huntId}/validate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StepValidationResultDTO> validateHuntStep(@PathVariable Long huntId,
            @RequestParam("imageFile") MultipartFile image, @RequestHeader("Session-id") String sessionId) {
        return ResponseEntity.ok(scavengerHuntStepFacade.validateStep(sessionId, huntId, image));
    }

    @PostMapping(value = "/{huntId}/reveal")
    public ResponseEntity<StepValidationResultDTO> revealHuntStep(@PathVariable Long huntId,
            @RequestHeader("Session-id") String sessionId) {
        return ResponseEntity.ok(scavengerHuntStepFacade.revealStep(huntId, sessionId));
    }

}
