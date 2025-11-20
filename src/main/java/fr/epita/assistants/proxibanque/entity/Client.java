package fr.epita.assistants.proxibanque.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "conseiller_id")
    private Conseiller conseiller;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Compte> comptes;

//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private List<CarteBancaire> cartes;
}