package za.ac.up.artifactup.unit.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.dto.mapper.ArtefactMapper;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.entity.Museum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArtefactMapperTest {

    private final ArtefactMapper mapper = Mappers.getMapper(ArtefactMapper.class);

    @Test
    void testToDTO() {
        Museum museum = new Museum();
        museum.setName("Natural History");

        Collection collection = new Collection();
        collection.setName("Dinosaurs");

        Artefact artefact = new Artefact();
        artefact.setTitle("T-Rex Skull");
        artefact.setImageUrl("image/path.png");
        artefact.setCollection(collection);
        artefact.setMuseum(museum);

        ArtefactDTO dto = mapper.toDTO(artefact);

        assertEquals("T-Rex Skull", dto.getTitle());
        assertEquals("Dinosaurs", dto.getType());
        assertEquals("Natural History", dto.getMuseumName());
        assertNotNull(dto.getImageUrl());
    }

    @Test
    void testToDTOs() {
        Artefact artefact = new Artefact();
        artefact.setTitle("Fossil");
        Collection collection = new Collection();
        collection.setName("Paleontology");
        artefact.setCollection(collection);
        Museum museum = new Museum();
        museum.setName("Science");
        artefact.setMuseum(museum);

        List<ArtefactDTO> dtos = mapper.toDTOs(List.of(artefact));
        assertEquals(1, dtos.size());
        assertEquals("Fossil", dtos.get(0).getTitle());
    }
}
