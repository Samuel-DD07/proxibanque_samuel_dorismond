package fr.epita.assistants.proxibanque.controller;

import fr.epita.assistants.proxibanque.entity.Agence;
import fr.epita.assistants.proxibanque.service.AgenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agences")
public class AgenceController {

    private final AgenceService agenceService;

    @GetMapping
    List<Agence> getAgences() {
        return agenceService.getAgences();
    }

    @PostMapping
    ResponseEntity<Agence> createAgence(@RequestBody Agence agence) {
        try {
            Agence savedAgence = agenceService.save(agence);
            return ResponseEntity.status(201).body(savedAgence);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Agence> getAgence(@PathVariable String id) {

        return agenceService.getAgence(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Agence> updateAgence(@PathVariable String id, @RequestBody Agence agenceDetails) {
        try {
            Agence updatedAgence = agenceService.update(id, agenceDetails);
            return ResponseEntity.ok(updatedAgence);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAgence(@PathVariable String id) {
        try {
            agenceService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build();
        }
    }
}