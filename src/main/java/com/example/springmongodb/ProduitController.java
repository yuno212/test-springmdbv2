package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @PostMapping
    public Produit addProduit(@Valid @RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produit> getProduitById(@PathVariable String id) {
        return produitRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable String id, @Valid @RequestBody Produit produitDetails) {
        return produitRepository.findById(id)
                .map(produit -> {
                    produit.setNomProduit(produitDetails.getNomProduit());
                    produit.setDescription(produitDetails.getDescription());
                    produit.setPrix(produitDetails.getPrix());
                    produit.setCategorie(produitDetails.getCategorie());
                    produit.setStock(produitDetails.getStock());
                    return produitRepository.save(produit);
                })
                .orElseGet(() -> {
                    produitDetails.setId(id);
                    return produitRepository.save(produitDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable String id) {
        produitRepository.deleteById(id);
    }
}
