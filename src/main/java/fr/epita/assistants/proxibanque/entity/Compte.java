package fr.epita.assistants.proxibanque.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCompte;

    private double solde;
    private LocalDate dateOuverture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    private Double decouvertAutorise = 1000.0;

    private Double tauxRemuneration = 0.03;

    public Compte() {
        this.dateOuverture = LocalDate.now();
        this.solde = 0.0;
    }
}