package za.ac.up.artefactup.repository;

import za.ac.up.artefactup.entity.ScavengerHunt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ScavengerHuntRepositoryTest {

    @Autowired
    private ScavengerHuntRepository scavengerHuntRepository;

    @Test
    public void testSaveAndFind() {
        ScavengerHunt obj = new ScavengerHunt();
        scavengerHuntRepository.save(obj);

        List<ScavengerHunt> results = scavengerHuntRepository.findAll();
        assertThat(results).isNotEmpty();
    }
}
