package za.ac.up.artifactup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScavengerHuntStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "step_seq")
    @SequenceGenerator(name = "step_seq", sequenceName = "step_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hunt_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ScavengerHunt hunt;

    @Column(nullable = false)
    private int stepNumber;

    @ManyToOne
    @JoinColumn(name = "artefact_id", nullable = false)
    private Artefact artefact;

    @ManyToOne
    @JoinColumn(name = "museum_id", nullable = false)
    private Museum museum;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String clue;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String hint;

}
