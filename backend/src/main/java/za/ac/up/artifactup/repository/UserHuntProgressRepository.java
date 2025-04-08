package za.ac.up.artifactup.repository;

import org.springframework.data.jpa.repository.*;
import za.ac.up.artifactup.entity.*;

import java.util.*;

public interface UserHuntProgressRepository extends JpaRepository<UserHuntProgress, Long> {

    Optional<UserHuntProgress> findByCognitoUserIdAndHuntId(String cognitoUserId, Long huntId);
}
