package za.ac.up.artifactup;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Test Application class for testing purposes.
 * Includes necessary configurations for test context to load properly.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "za.ac.up.artifactup.repository")
@EntityScan(basePackages = "za.ac.up.artifactup.entity")
@ComponentScan(basePackages = "za.ac.up.artifactup")
public class TestBackendApplication {
}