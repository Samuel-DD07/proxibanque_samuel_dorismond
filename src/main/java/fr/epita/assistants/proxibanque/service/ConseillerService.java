package fr.epita.assistants.proxibanque.service;

import fr.epita.assistants.proxibanque.entity.Conseiller;
import java.util.List;
import java.util.Optional;

public interface ConseillerService {
    List<Conseiller> getConseillers();

    Optional<Conseiller> getConseiller(Long id);

    Conseiller save(Conseiller conseiller);

    Conseiller update(Long id, Conseiller conseillerDetails);

    void delete(Long id);
}