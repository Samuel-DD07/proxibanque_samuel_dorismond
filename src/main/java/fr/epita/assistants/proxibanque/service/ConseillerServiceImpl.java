package fr.epita.assistants.proxibanque.service;

import fr.epita.assistants.proxibanque.entity.Conseiller;
import fr.epita.assistants.proxibanque.repository.AgenceRepository;
import fr.epita.assistants.proxibanque.repository.ConseillerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConseillerServiceImpl implements ConseillerService {

    private final ConseillerRepository conseillerRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public List<Conseiller> getConseillers() {
        return conseillerRepository.findAll();
    }

    @Override
    public Optional<Conseiller> getConseiller(Long id) {
        return conseillerRepository.findById(id);
    }

    @Override
    public Conseiller save(Conseiller conseiller) {
        if (conseiller.getAgence() != null && conseiller.getAgence().getId() != null) {
            if (!agenceRepository.existsById(conseiller.getAgence().getId())) {
                throw new IllegalArgumentException("L'agence spécifiée n'existe pas.");
            }
        } else {
            throw new IllegalArgumentException("Un conseiller doit être rattaché à une agence.");
        }

        return conseillerRepository.save(conseiller);
    }

    @Override
    public Conseiller update(Long id, Conseiller conseillerDetails) {
        Optional<Conseiller> conseillerOptional = conseillerRepository.findById(id);

        if (conseillerOptional.isEmpty()) {
            throw new IllegalArgumentException("Conseiller non trouvé pour l'ID : " + id);
        }

        Conseiller conseillerExistant = conseillerOptional.get();

        if (conseillerDetails.getNom() != null) {
            conseillerExistant.setNom(conseillerDetails.getNom());
        }
        if (conseillerDetails.getPrenom() != null) {
            conseillerExistant.setPrenom(conseillerDetails.getPrenom());
        }

        if (conseillerDetails.getAgence() != null && conseillerDetails.getAgence().getId() != null) {
            if (!agenceRepository.existsById(conseillerDetails.getAgence().getId())) {
                throw new IllegalArgumentException("L'agence spécifiée n'existe pas.");
            }
            conseillerExistant.setAgence(conseillerDetails.getAgence());
        }

        return conseillerRepository.save(conseillerExistant);
    }

    @Override
    public void delete(Long id) {
        if (!conseillerRepository.existsById(id)) {
            throw new IllegalArgumentException("Impossible de supprimer : Conseiller non trouvé pour l'ID : " + id);
        }

        conseillerRepository.deleteById(id);
    }
}