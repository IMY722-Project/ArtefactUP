package za.ac.up.artefactup.controller;

import za.ac.up.artefactup.entity.ChatBot;
import za.ac.up.artefactup.repository.ChatBotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ChatBotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChatBotRepository chatBotRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/chatbots"))
            .andExpect(status().isOk());
    }
}
