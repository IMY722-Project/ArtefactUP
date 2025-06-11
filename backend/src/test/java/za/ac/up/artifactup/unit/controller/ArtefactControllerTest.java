package za.ac.up.artifactup.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.ac.up.artifactup.controller.ArtefactController;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.service.ArtefactService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtefactController.class)
public class ArtefactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtefactService<ArtefactDTO> serviceFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllArtefacts() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        when(serviceFacade.findAll()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/api/artefact"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldCreateArtefact() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        when(serviceFacade.create(any(ArtefactDTO.class))).thenReturn(dto);

        mockMvc.perform(multipart("/api/artefact/create")
                .flashAttr("artefactDTO", dto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdateArtefact() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        when(serviceFacade.create(any(ArtefactDTO.class))).thenReturn(dto);

        mockMvc.perform(put("/api/artefact/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldDeleteArtefact() throws Exception {
        Long id = 1L;
        doNothing().when(serviceFacade).deleteById(id);

        mockMvc.perform(delete("/api/artefact/delete/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFindByCollectionName() throws Exception {
        String collectionName = "Art";
        when(serviceFacade.findAllArtifactsByCollectionName(eq(collectionName)))
                .thenReturn(Collections.singletonList(new ArtefactDTO()));

        mockMvc.perform(get("/api/artefact/collection/{collectionName}", collectionName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldFindByMuseumName() throws Exception {
        String museumName = "National";
        when(serviceFacade.findAllArtefactsByMuseumName(eq(museumName)))
                .thenReturn(Collections.singletonList(new ArtefactDTO()));

        mockMvc.perform(get("/api/artefact/museum/{museumName}", museumName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
