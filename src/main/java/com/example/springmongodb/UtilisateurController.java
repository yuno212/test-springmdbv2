package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public Utilisateur addUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> getUtilisateurById(@PathVariable String id) {
        return utilisateurRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable String id, @Valid @RequestBody Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id)
                .map(utilisateur -> {
                    utilisateur.setIdUtilisateur(utilisateurDetails.getIdUtilisateur());
                    utilisateur.setNom(utilisateurDetails.getNom());
                    utilisateur.setEmail(utilisateurDetails.getEmail());
                    utilisateur.setAdresse(utilisateurDetails.getAdresse());
                    return utilisateurRepository.save(utilisateur);
                })
                .orElseGet(() -> {
                    utilisateurDetails.setId(id);
                    return utilisateurRepository.save(utilisateurDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable String id) {
        utilisateurRepository.deleteById(id);
    }
}
