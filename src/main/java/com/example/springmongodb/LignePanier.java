package com.example.springmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lignePanier")
public class LignePanier {
    @Id
    private String id;
    private int idLignePanier;
    private int idPanier;
    private int idProduit;
    private int quantite;

    // Constructors
    public LignePanier() {}

    public LignePanier(int idLignePanier, int idPanier, int idProduit, int quantite) {
        this.idLignePanier = idLignePanier;
        this.idPanier = idPanier;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdLignePanier() {
        return idLignePanier;
    }

    public void setIdLignePanier(int idLignePanier) {
        this.idLignePanier = idLignePanier;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
