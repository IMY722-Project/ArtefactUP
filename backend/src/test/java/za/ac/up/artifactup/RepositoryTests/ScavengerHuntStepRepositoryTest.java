package za.ac.up.artefactup.repository;

import za.ac.up.artefactup.entity.ScavengerHuntStep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ScavengerHuntStepRepositoryTest {

    @Autowired
    private ScavengerHuntStepRepository scavengerHuntStepRepository;

    @Test
    public void testSaveAndFind() {
        ScavengerHuntStep obj = new ScavengerHuntStep();
        scavengerHuntStepRepository.save(obj);

        List<ScavengerHuntStep> results = scavengerHuntStepRepository.findAll();
        assertThat(results).isNotEmpty();
    }
}
