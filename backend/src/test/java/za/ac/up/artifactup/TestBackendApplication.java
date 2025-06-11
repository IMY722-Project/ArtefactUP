package za.ac.up.artifactup;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a dummy Application class for testing purposes.
 * When the tests run, Spring will find and use this class to build the
 * ApplicationContext instead of the real BackendApplication.
 *
 * Crucially, this class does NOT have the @EnableJpaRepositories annotation,
 * which prevents the database layer from being loaded during unit tests.
 */
@SpringBootApplication
public class TestBackendApplication {
}