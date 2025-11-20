package fr.epita.assistants.proxibanque.entity;

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
    private Agence agence;

    @OneToMany(mappedBy = "conseiller")
    private List<Client> clients;
}
