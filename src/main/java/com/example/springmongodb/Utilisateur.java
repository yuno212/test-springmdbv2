package com.example.springmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utilisateurs")
public class Utilisateur {
    @Id
    private String id;
    private String idUtilisateur;
    private String nom;
    private String email;
    private String adresse;

    // Constructors
    public Utilisateur() {}

    public Utilisateur(String idUtilisateur, String nom, String email, String adresse) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}