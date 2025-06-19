package za.ac.up.artifactup.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScavengerHunt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hunt_seq")
    @SequenceGenerator(name = "hunt_seq", sequenceName = "hunt_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "hunt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScavengerHuntStep> steps = new ArrayList<>();
}
