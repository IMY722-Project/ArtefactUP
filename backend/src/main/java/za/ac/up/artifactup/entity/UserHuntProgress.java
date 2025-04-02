package za.ac.up.artifactup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHuntProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cognitoUserId;

    @ManyToOne
    @JoinColumn(name = "hunt_id", nullable = false)
    private ScavengerHunt hunt;

    @Column(nullable = false)
    private int currentStep = 1;

    @Column(nullable = false)
    private boolean completed = false;
}
