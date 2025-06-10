package za.ac.up.artefactup.controller;

import za.ac.up.artefactup.entity.UserHuntProgress;
import za.ac.up.artefactup.repository.UserHuntProgressRepository;
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
public class UserHuntProgressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserHuntProgressRepository userHuntProgressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/userhuntprogresss"))
            .andExpect(status().isOk());
    }
}
