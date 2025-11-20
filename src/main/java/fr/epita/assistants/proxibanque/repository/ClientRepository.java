package fr.epita.assistants.proxibanque.repository;

import fr.epita.assistants.proxibanque.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByConseillerId(Long conseillerId);
}