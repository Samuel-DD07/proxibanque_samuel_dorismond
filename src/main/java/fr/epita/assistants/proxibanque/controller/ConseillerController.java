package fr.epita.assistants.proxibanque.controller;

import fr.epita.assistants.proxibanque.entity.Conseiller;
import fr.epita.assistants.proxibanque.service.ConseillerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conseillers")
public class ConseillerController {

    private final ConseillerService conseillerService;

    @GetMapping
    List<Conseiller> getConseillers() {
        return conseillerService.getConseillers();
    }

    @GetMapping("/{id}")
    ResponseEntity<Conseiller> getConseiller(@PathVariable Long id) {
        return conseillerService.getConseiller(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    ResponseEntity<Conseiller> createConseiller(@RequestBody Conseiller conseiller) {
        Conseiller savedConseiller = conseillerService.save(conseiller);
        return ResponseEntity.status(201).body(savedConseiller);
    }

    @PutMapping("/{id}")
    ResponseEntity<Conseiller> updateConseiller(@PathVariable Long id, @RequestBody Conseiller conseillerDetails) {
        try {
            Conseiller updatedConseiller = conseillerService.update(id, conseillerDetails);
            return ResponseEntity.ok(updatedConseiller);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteConseiller(@PathVariable Long id) {
        try {
            conseillerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
    }
}