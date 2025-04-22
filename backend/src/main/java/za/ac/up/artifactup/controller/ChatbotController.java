package za.ac.up.artifactup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.up.artifactup.dto.ChatRequestDTO;
import za.ac.up.artifactup.dto.ChatResponseDTO;
import za.ac.up.artifactup.service.ChatbotService;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping("/ask")
    public ResponseEntity<ChatResponseDTO> askChatbot(@RequestBody ChatRequestDTO request) {
        ChatResponseDTO response = chatbotService.ask(request.getMessage());
        return ResponseEntity.ok(response);
    }
}
