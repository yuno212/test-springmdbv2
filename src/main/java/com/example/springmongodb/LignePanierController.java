package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lignePanier")
public class LignePanierController {

    @Autowired
    private LignePanierRepository lignePanierRepository;

    @PostMapping
    public LignePanier addLignePanier(@Valid @RequestBody LignePanier lignePanier) {
        return lignePanierRepository.save(lignePanier);
    }

    @GetMapping
    public List<LignePanier> getAllLignePanier() {
        return lignePanierRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LignePanier> getLignePanierById(@PathVariable String id) {
        return lignePanierRepository.findById(id);
    }

    @PutMapping("/{id}")
    public LignePanier updateLignePanier(@PathVariable String id, @Valid @RequestBody LignePanier lignePanierDetails) {
        return lignePanierRepository.findById(id)
                .map(lignePanier -> {
                    lignePanier.setIdLignePanier(lignePanierDetails.getIdLignePanier());
                    lignePanier.setIdPanier(lignePanierDetails.getIdPanier());
                    lignePanier.setIdProduit(lignePanierDetails.getIdProduit());
                    lignePanier.setQuantite(lignePanierDetails.getQuantite());
                    return lignePanierRepository.save(lignePanier);
                })
                .orElseGet(() -> {
                    lignePanierDetails.setId(id);
                    return lignePanierRepository.save(lignePanierDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteLignePanier(@PathVariable String id) {
        lignePanierRepository.deleteById(id);
    }
}
