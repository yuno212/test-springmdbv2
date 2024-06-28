package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementRepository paiementRepository;

    @PostMapping
    public Paiement addPaiement(@Valid @RequestBody Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Paiement> getPaiementById(@PathVariable String id) {
        return paiementRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable String id, @Valid @RequestBody Paiement paiementDetails) {
        return paiementRepository.findById(id)
                .map(paiement -> {
                    paiement.setIdCommande(paiementDetails.getIdCommande());
                    paiement.setDatePaiement(paiementDetails.getDatePaiement());
                    paiement.setMontant(paiementDetails.getMontant());
                    paiement.setMethodePaiement(paiementDetails.getMethodePaiement());
                    return paiementRepository.save(paiement);
                })
                .orElseGet(() -> {
                    paiementDetails.setId(id);
                    return paiementRepository.save(paiementDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable String id) {
        paiementRepository.deleteById(id);
    }
}
