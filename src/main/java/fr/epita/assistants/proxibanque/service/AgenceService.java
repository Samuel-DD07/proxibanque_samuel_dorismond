package fr.epita.assistants.proxibanque.service;

import fr.epita.assistants.proxibanque.entity.Agence;
import java.util.List;
import java.util.Optional;

public interface AgenceService {
    List<Agence> getAgences();

    Optional<Agence> getAgence(String id);

    Agence save(Agence agence);

    Agence update(String id, Agence agenceDetails);

    void delete(String id);
}