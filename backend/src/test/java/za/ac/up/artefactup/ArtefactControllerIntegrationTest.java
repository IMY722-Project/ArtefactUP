package za.ac.up.artefactup;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.up.artefactup.controller.ArtefactController;
import za.ac.up.artefactup.dto.ArtefactDTO;
import za.ac.up.artefactup.service.ArtefactService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ArtefactControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ArtefactController artefactController;

    @Mock
    private ArtefactService<ArtefactDTO> artefactService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(artefactController).build();
        ArtefactDTO artefact = new ArtefactDTO();
        artefact.setId(1L);
        artefact.setTitle("Ancient Vase");
        artefact.setCreator("Unknown");
        artefact.setDescription("A vase from 500 BC");
        List<ArtefactDTO> artefacts = List.of(artefact);
        when(artefactService.findAll()).thenReturn(artefacts);
    }

    @Test
    public void testGetArtefactById() throws Exception {
        mockMvc.perform(get("/api/artefact")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Ancient Vase"));
    }
}
