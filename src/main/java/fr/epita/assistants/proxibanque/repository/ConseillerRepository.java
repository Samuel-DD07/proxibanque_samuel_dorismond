package fr.epita.assistants.proxibanque.repository;

import fr.epita.assistants.proxibanque.entity.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
}