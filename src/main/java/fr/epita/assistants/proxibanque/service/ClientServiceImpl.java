package fr.epita.assistants.proxibanque.service;

import fr.epita.assistants.proxibanque.entity.Client;
import fr.epita.assistants.proxibanque.entity.Conseiller;
import fr.epita.assistants.proxibanque.repository.ClientRepository;
import fr.epita.assistants.proxibanque.repository.ConseillerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        if (client.getConseiller() == null || client.getConseiller().getId() == null) {
            throw new IllegalArgumentException("Un client doit être rattaché à un conseiller.");
        }

        Conseiller conseillerComplet = conseillerRepository.findById(client.getConseiller().getId())
                .orElseThrow(() -> new IllegalArgumentException("Conseiller inexistant."));

        client.setConseiller(conseillerComplet);

        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client update(Long id, Client clientDetails) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new IllegalArgumentException("Client non trouvé pour l'ID : " + id);
        }

        Client clientExistant = clientOptional.get();

        if (clientDetails.getNom() != null) {
            clientExistant.setNom(clientDetails.getNom());
        }
        if (clientDetails.getPrenom() != null) {
            clientExistant.setPrenom(clientDetails.getPrenom());
        }

        if (clientDetails.getAdresse() != null) {
            clientExistant.setAdresse(clientDetails.getAdresse());
        }

        if (clientDetails.getCodePostal() != null) {
            clientExistant.setCodePostal(clientDetails.getCodePostal());
        }
        if (clientDetails.getTelephone() != null) {
            clientExistant.setTelephone(clientDetails.getTelephone());
        }
        if (clientDetails.getVille() != null) {
            clientExistant.setVille(clientDetails.getVille());
        }

        if (clientDetails.getConseiller() != null && clientDetails.getConseiller().getId() != null) {
            Long nouveauConseillerId = clientDetails.getConseiller().getId();

            Conseiller nouveauConseiller = conseillerRepository.findById(nouveauConseillerId)
                    .orElseThrow(() -> new IllegalArgumentException("Le nouveau conseiller (ID: " + nouveauConseillerId + ") spécifié n'existe pas."));

            clientExistant.setConseiller(nouveauConseiller);
        }

        return clientRepository.save(clientExistant);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Impossible de supprimer : Client non trouvé pour l'ID :: " + id);
        }
        clientRepository.deleteById(id);
    }
}