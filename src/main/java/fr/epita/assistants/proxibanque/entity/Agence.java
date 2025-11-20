package fr.epita.assistants.proxibanque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Agence {

    @Id
    @Column(length = 5)
    private String id;

    private Date dateCreation = new Date();;
    private String gerant;

    @JsonIgnore
    @OneToMany(mappedBy = "agence", cascade = CascadeType.REMOVE)
    private List<Conseiller> conseillers;
}
