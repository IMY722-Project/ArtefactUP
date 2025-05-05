package za.ac.up.artifactup.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.ac.up.artifactup.dto.ChatResponseDTO;
import za.ac.up.artifactup.service.ChatbotService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Override
    public ChatResponseDTO ask(String message) {
        OpenAiService service = new OpenAiService(openaiApiKey);

        ChatMessage userMessage = new ChatMessage("user", message);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Collections.singletonList(userMessage))
                .maxTokens(200)
                .build();

        String responseText = service.createChatCompletion(request)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();

        return new ChatResponseDTO(responseText.trim());
    }
}
