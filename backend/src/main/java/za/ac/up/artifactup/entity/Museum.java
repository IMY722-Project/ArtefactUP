package za.ac.up.artifactup.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Museum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "museum_seq")
    @SequenceGenerator(name = "museum_seq", sequenceName = "museum_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpeningHours> openingHours;

    @Column(nullable = false)
    private String imageUrl;
}
