package za.ac.up.artifactup.service;

import za.ac.up.artifactup.dto.ChatResponseDTO;

public interface ChatbotService {
    ChatResponseDTO ask(String message);
}
