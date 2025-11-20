package fr.epita.assistants.proxibanque.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CarteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCarte;

    private TypeCarte type;

    private boolean active = true;

    @ManyToOne
    private Client client;
}