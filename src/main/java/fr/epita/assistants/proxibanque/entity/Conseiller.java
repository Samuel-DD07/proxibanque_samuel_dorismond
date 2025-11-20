package fr.epita.assistants.proxibanque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Conseiller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    @JsonIgnore
    @OneToMany(mappedBy = "conseiller")
    private List<Client> clients;
}
