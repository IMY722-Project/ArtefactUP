package za.ac.up.artifactup.RepositoryTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.repository.ArtefactRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(basePackages = "za.ac.up.artifactup") // Ensures Spring scans entities & config
public class ArtefactRepositoryTest {

    @Autowired
    private ArtefactRepository artefactRepository;

    @Test
    void testSaveAndFindArtefact() {
        Artefact artefact = new Artefact();
        artefact.setName("Test Artefact");

        artefact = artefactRepository.save(artefact);
        Artefact found = artefactRepository.findById(artefact.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Test Artefact");
    }
}
