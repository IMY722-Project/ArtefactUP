package za.ac.up.artifactup;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test") // This ensures the bean only runs during tests
public class PropertiesLogger implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            Session session = entityManager.unwrap(Session.class);
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
            String dialect = sessionFactory.getJdbcServices().getDialect().toString();

            System.out.println("\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!! HIBERNATE DIALECT IN USE: " + dialect + " !!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
        } catch (Exception e) {
            System.err.println("!!! Could not retrieve Hibernate dialect: " + e.getMessage());
        }
    }
}