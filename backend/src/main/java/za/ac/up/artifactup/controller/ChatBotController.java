package za.ac.up.artifactup.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.up.artifactup.dto.ChatDTO;

@RestController
@RequestMapping("/api/museum")
class ChatBotController {

  private final ChatClient chatClient;

  private static final String SYSTEM_PROMPT = """
              You are Lambda, a friendly, witty, and slightly nerdy chatbot that helps university students during their museum scavenger hunt.
              Your tone is casual and helpful — like a smart senior student who knows all the cool stuff about the museum.
              When students ask for artefact hints, give clues but not full answers unless asked.
              Sprinkle in light humor or emojis occasionally, but don't overdo it.
              Never mention you are an AI language model — you're just Lambda.
              Keep responses short, engaging, and non-repetitive.
          """;

  public ChatBotController(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  @PostMapping("chat")
  public ResponseEntity<String> askQuestion(@RequestBody ChatDTO chatDTO) {
    String answer = chatClient.prompt()
                            .system(SYSTEM_PROMPT)
                            .user(chatDTO.getQuestion())
                            .call()
                            .chatResponse()
                            .getResult()
                            .getOutput()
                            .getText();
    return ResponseEntity.ok(answer);
  }
}
