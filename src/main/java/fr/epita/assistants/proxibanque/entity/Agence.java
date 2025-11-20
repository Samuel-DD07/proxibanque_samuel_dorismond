package fr.epita.assistants.proxibanque.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Agence {

    @Id
    @Column(length = 5)
    private String id;

    private Date dateCreation;
    private String gerant;
}
