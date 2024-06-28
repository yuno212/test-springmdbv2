package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    @Autowired
    private PanierRepository panierRepository;

    @PostMapping
    public Panier addPanier(@RequestBody Panier panier) {
        return panierRepository.save(panier);
    }

    @GetMapping
    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    @GetMapping("/{id}")
    public Panier getPanierById(@PathVariable String id) {
        return panierRepository.findById(id).orElse(null);
    }
}
