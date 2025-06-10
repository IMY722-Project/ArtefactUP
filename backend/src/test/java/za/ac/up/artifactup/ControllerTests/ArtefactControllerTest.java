package za.ac.up.artefactup.controller;

import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.repository.ArtefactRepository;
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
public class ArtefactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtefactRepository artefactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllArtefacts() throws Exception {
        Artefact a = new Artefact();
        a.setName("A1");
        a.setDescription("Desc");
        artefactRepository.save(a);

        mockMvc.perform(get("/api/artefacts"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateArtefact() throws Exception {
        Artefact a = new Artefact();
        a.setName("New Artefact");
        a.setDescription("New Desc");

        mockMvc.perform(post("/api/artefacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(a)))
                .andExpect(status().isOk());
    }
}
