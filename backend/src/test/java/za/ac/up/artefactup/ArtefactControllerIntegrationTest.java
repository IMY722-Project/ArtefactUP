package za.ac.up.artefactup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.repository.ArtefactRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtefactControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtefactRepository artefactRepository;

    @BeforeEach
    public void setup() {
        artefactRepository.deleteAll(); // clean before test
        Artefact artefact = new Artefact();
        artefact.setId(1L);
        artefact.setTitle("Ancient Vase");
        artefact.setCreator("Unknown");
        artefact.setDescription("A vase from 500 BC");
        artefactRepository.save(artefact);
    }

    @Test
    public void testGetArtefactById() throws Exception {
        mockMvc.perform(get("/api/artefacts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Ancient Vase"));
    }
}
