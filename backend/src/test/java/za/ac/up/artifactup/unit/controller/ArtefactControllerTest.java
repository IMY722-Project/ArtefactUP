package za.ac.up.artifactup.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// --- Import all necessary classes ---
import za.ac.up.artifactup.TestBackendApplication;
import za.ac.up.artifactup.config.BucketConfig;
import za.ac.up.artifactup.controller.*;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.dto.qualifier.ArtefactQualifier;
import za.ac.up.artifactup.dto.qualifier.MuseumQualifier;
import za.ac.up.artifactup.service.ArtefactService;
import software.amazon.awssdk.services.s3.S3Client;

// The Auto-Configuration class to EXCLUDE
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Add the excludeAutoConfiguration attribute to disable the entire OpenAI module
@SpringBootTest(classes = TestBackendApplication.class, properties = "spring.autoconfigure.exclude=org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration")
@AutoConfigureMockMvc
public class ArtefactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --- Mocks for the Controller Under Test ---
    @MockBean
    private ArtefactService<ArtefactDTO> serviceFacade;
    @MockBean
    private MuseumQualifier museumQualifier;
    @MockBean
    private ArtefactQualifier artefactQualifier;

    // --- Mocks for AWS ---
    @MockBean
    private BucketConfig bucketConfig;
    @MockBean
    private S3Client s3Client;

    // --- Mocks for Other Controllers (as a safety net) ---
    @MockBean
    private ChatBotController chatBotController;
    @MockBean
    private MuseumController museumController;
    @MockBean
    private ScavengerHuntController scavengerHuntController;
    @MockBean
    private ScavengerHuntStepController scavengerHuntStepController;
    @MockBean
    private UserHuntProgressController userHuntProgressController;

    @Test
    void shouldReturnAllArtefacts() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        when(serviceFacade.findAll()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/api/artefact"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // The rest of your test methods...
    @Test
    void shouldCreateArtefact() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        when(serviceFacade.create(any(ArtefactDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/artefact/create")
                .flashAttr("artefactDTO", dto))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateArtefact() throws Exception {
        ArtefactDTO dto = new ArtefactDTO();
        when(serviceFacade.create(any(ArtefactDTO.class))).thenReturn(dto);

        mockMvc.perform(put("/api/artefact/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
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