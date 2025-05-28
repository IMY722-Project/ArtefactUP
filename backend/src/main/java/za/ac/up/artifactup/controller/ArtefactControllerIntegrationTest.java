// backend/src/test/java/za/ac/up/artifactup/controller/ArtifactControllerIntegrationTest.java
package za.ac.up.artifactup.controller;
/*
 * imports not working please resolve
 * 
 * // backend/src/test/java/za/ac/up/artifactup/controller/
 * ArtifactControllerIntegrationTest.java
 * package za.ac.up.artifactup.controller;
 * 
 * import za.ac.up.artifactup.model.Artifact;
 * import za.ac.up.artifactup.repository.ArtifactRepository;
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.http.MediaType;
 * import org.springframework.test.web.servlet.MockMvc;
 * 
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc
 * public class ArtifactControllerIntegrationTest {
 * 
 * @Autowired
 * private MockMvc mockMvc;
 * 
 * @Autowired
 * private ArtifactRepository artifactRepository;
 * 
 * @BeforeEach
 * public void setup() {
 * artifactRepository.save(new Artifact(1L, "Ancient Vase",
 * "A vase from 500 BC"));
 * }
 * 
 * @Test
 * public void testGetArtifactById() throws Exception {
 * mockMvc.perform(get("/api/artifacts/1")
 * .contentType(MediaType.APPLICATION_JSON))
 * .andExpect(status().isOk())
 * .andExpect(jsonPath("$.name").value("Ancient Vase"));
 * }
 * }
 * 
 * 
 */
