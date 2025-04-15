package za.ac.up.artifactup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.up.artifactup.dto.UserHuntProgressDTO;
import za.ac.up.artifactup.service.impl.UserHuntProgressFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hunts/progress")
public class UserHuntProgressController {

    private final UserHuntProgressFacade userHuntProgressFacade;

    @GetMapping("{huntId}")
    public ResponseEntity<UserHuntProgressDTO> getUserProgress(@PathVariable Long huntId, @RequestHeader("Session-id") String sessionId) {
        return ResponseEntity.ok(userHuntProgressFacade.getUserProgress(huntId, sessionId));
    }

    @PostMapping("/start/{huntId}")
    public ResponseEntity<Void> startHunt(@PathVariable Long huntId, @RequestHeader("Session-id") String sessionId) {
        userHuntProgressFacade.startHunt(huntId, sessionId);
        return ResponseEntity.noContent().build();
    }
}
