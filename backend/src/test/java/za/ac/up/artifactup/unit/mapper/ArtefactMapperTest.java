package za.ac.up.artifactup.unit.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.dto.mapper.ArtefactMapper;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.entity.Museum;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ArtefactMapperIntegrationTest {

    @Autowired
    private ArtefactMapper artefactMapper;

    @Test
    void testToDTO() {
        // Create test entity
        Museum museum = new Museum();
        museum.setName("Test Museum");

        Collection collection = new Collection();
        collection.setName("Test Collection");

        Artefact artefact = new Artefact();
        artefact.setId(1L);
        artefact.setTitle("Test Title");
        artefact.setMuseum(museum);
        artefact.setCollection(collection);
        artefact.setImageUrl("test.jpg");

        ArtefactDTO dto = artefactMapper.toDTO(artefact);

        assertNotNull(dto);
        assertEquals("Test Title", dto.getTitle());
        assertEquals("Test Museum", dto.getMuseumName());
        assertEquals("Test Collection", dto.getType());
    }
}