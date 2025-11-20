package fr.epita.assistants.proxibanque.service;

import fr.epita.assistants.proxibanque.entity.Client;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getClients();

    Optional<Client> getClient(Long id);

    Client save(Client client);

    Client update(Long id, Client clientDetails);

    void delete(Long id);
}