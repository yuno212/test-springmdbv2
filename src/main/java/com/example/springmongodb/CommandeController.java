package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @PostMapping
    public Commande addCommande(@Valid @RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Commande> getCommandeById(@PathVariable String id) {
        return commandeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable String id, @Valid @RequestBody Commande commandeDetails) {
        return commandeRepository.findById(id)
                .map(commande -> {
                    commande.setDateCommande(commandeDetails.getDateCommande());
                    commande.setStatut(commandeDetails.getStatut());
                    commande.setIdUtilisateur(commandeDetails.getIdUtilisateur());
                    commande.setMontantTotal(commandeDetails.getMontantTotal());
                    commande.setProduits(commandeDetails.getProduits());
                    return commandeRepository.save(commande);
                })
                .orElseGet(() -> {
                    commandeDetails.setId(id);
                    return commandeRepository.save(commandeDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable String id) {
        commandeRepository.deleteById(id);
    }
}
