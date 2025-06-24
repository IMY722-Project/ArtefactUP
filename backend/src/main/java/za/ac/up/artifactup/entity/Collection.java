package za.ac.up.artifactup.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_seq")
    @SequenceGenerator(name = "collection_seq", sequenceName = "collection_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

}
