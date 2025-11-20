package fr.epita.assistants.proxibanque.service;

import fr.epita.assistants.proxibanque.entity.Agence;
import fr.epita.assistants.proxibanque.repository.AgenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    public List<Agence> getAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Optional<Agence> getAgence(String id) {
        return agenceRepository.findById(id);
    }
    
    @Override 
    public Agence save(Agence agence) {
        String id = agence.getId();

        if (agenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Une agence avec cet ID existe déjà.");
        }

        return agenceRepository.save(agence);
    }

    @Override
    public Agence update(String id, Agence agenceDetails) {
        Optional<Agence> agenceOptional = agenceRepository.findById(id);

        if (agenceOptional.isEmpty()) {
            throw new IllegalArgumentException("Agence non trouvée pour l'ID : " + id);
        }

        Agence agenceExistante = agenceOptional.get();

        if (agenceDetails.getGerant() != null && !agenceDetails.getGerant().isBlank()) {
            agenceExistante.setGerant(agenceDetails.getGerant());
        }

        return agenceRepository.save(agenceExistante);
    }

    @Override
    public void delete(String id) {

        if (!agenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Impossible de supprimer : Agence non trouvée pour l'ID :: " + id);
        }

        agenceRepository.deleteById(id);
    }
}