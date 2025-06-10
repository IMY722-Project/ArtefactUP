package za.ac.up.artifactup.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.ac.up.artifactup.controller.ArtefactController;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.service.ArtefactService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtefactController.class)
class ArtefactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtefactService<ArtefactDTO> artefactService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllArtefacts() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        dto.setTitle("Test Artefact");

        when(artefactService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/artefact"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Artefact"));
    }

    @Test
    void shouldCreateArtefact() throws Exception {
        ArtefactDTO input = new ArtefactDTO();
        input.setTitle("New Artefact");

        when(artefactService.create(any())).thenReturn(input);

        mockMvc.perform(multipart("/api/artefact/create")
                .flashAttr("artefactDTO", input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Artefact"));
    }

    @Test
    void shouldUpdateArtefact() throws Exception {
        ArtefactDTO input = new ArtefactDTO();
        input.setTitle("Updated Artefact");

        when(artefactService.create(any())).thenReturn(input);

        mockMvc.perform(put("/api/artefact/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Artefact"));
    }

    @Test
    void shouldDeleteArtefact() throws Exception {
        doNothing().when(artefactService).deleteById(1L);

        mockMvc.perform(delete("/api/artefact/delete/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFindByCollectionName() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        dto.setTitle("Collected Artefact");

        when(artefactService.findAllArtifactsByCollectionName("History")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/artefact/collection/History"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Collected Artefact"));
    }

    @Test
    void shouldFindByMuseumName() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        dto.setTitle("Museum Piece");

        when(artefactService.findAllArtefactsByMuseumName("Louvre")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/artefact/museum/Louvre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Museum Piece"));
    }
}
