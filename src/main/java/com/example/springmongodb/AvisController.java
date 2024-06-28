package com.example.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    @Autowired
    private AvisRepository avisRepository;

    @PostMapping
    public Avis addAvis(@Valid @RequestBody Avis avis) {
        return avisRepository.save(avis);
    }

    @GetMapping
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Avis> getAvisById(@PathVariable String id) {
        return avisRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Avis updateAvis(@PathVariable String id, @Valid @RequestBody Avis avisDetails) {
        return avisRepository.findById(id)
                .map(avis -> {
                    avis.setIdProduit(avisDetails.getIdProduit());
                    avis.setIdClient(avisDetails.getIdClient());
                    avis.setNote(avisDetails.getNote());
                    avis.setCommentaire(avisDetails.getCommentaire());
                    avis.setDateAvis(avisDetails.getDateAvis());
                    return avisRepository.save(avis);
                })
                .orElseGet(() -> {
                    avisDetails.setId(id);
                    return avisRepository.save(avisDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteAvis(@PathVariable String id) {
        avisRepository.deleteById(id);
    }
}
