package za.ac.up.artefactup.controller;

import za.ac.up.artefactup.model.Artefact;
import za.ac.up.artefactup.repository.ArtefactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtefactControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtefactRepository artefactRepository;

    @BeforeEach
    public void setup() {
        artefactRepository.save(new Artefact(1L, "Ancient Vase", "A vase from 500 BC"));
    }

    @Test
    public void testGetArtefactById() throws Exception {
        mockMvc.perform(get("/api/artefacts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ancient Vase"));
    }
}
