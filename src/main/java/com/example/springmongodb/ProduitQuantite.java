package com.example.springmongodb;

public class ProduitQuantite {

    private String produitId; // Using String instead of ObjectId for simplicity
    private int quantite;

    // Constructors, Getters, and Setters

    public ProduitQuantite() {
    }

    public ProduitQuantite(String produitId, int quantite) {
        this.produitId = produitId;
        this.quantite = quantite;
    }

    public String getProduitId() {
        return produitId;
    }

    public void setProduitId(String produitId) {
        this.produitId = produitId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
