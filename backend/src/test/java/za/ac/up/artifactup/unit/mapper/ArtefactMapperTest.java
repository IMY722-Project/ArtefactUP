package za.ac.up.artifactup.unit.mapper;

import org.junit.jupiter.api.Test;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.dto.mapper.ArtefactMapper;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class ArtefactMapperTest {

    private final ArtefactMapper artefactMapper = ArtefactMapper.INSTANCE;

    @Test
    void testToDTO() {
        Artefact artefact = new Artefact();
        artefact.setId(1L);
        artefact.setTitle("Ancient Vase");
        artefact.setDescription("A historical vase from the 15th century");

        Collection collection = new Collection();
        collection.setName("Antiquities");
        artefact.setCollection(collection);

        ArtefactDTO dto = artefactMapper.toDTO(artefact);

        assertNotNull(dto);
        assertEquals("Ancient Vase", dto.getTitle());
        assertEquals("Antiquities", dto.getCollectionName());
    }

    @Test
    void testToEntity() {
        ArtefactDTO dto = new ArtefactDTO();
        dto.setTitle("Golden Mask");
        dto.setCollectionName("Treasures");

        Artefact entity = artefactMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals("Golden Mask", entity.getTitle());
        assertNotNull(entity.getCollection());
        assertEquals("Treasures", entity.getCollection().getName());
    }
}
