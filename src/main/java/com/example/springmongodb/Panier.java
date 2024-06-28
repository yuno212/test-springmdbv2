package com.example.springmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "paniers")
public class Panier {

    @Id
    private String id; // Using String instead of ObjectId for simplicity

    private String clientId; // Assuming client_id is stored as String

    private List<ProduitQuantite> produits;

    private LocalDateTime dateCreation;

    // Constructors, Getters, and Setters

    public Panier() {
    }

    public Panier(String clientId, List<ProduitQuantite> produits, LocalDateTime dateCreation) {
        this.clientId = clientId;
        this.produits = produits;
        this.dateCreation = dateCreation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<ProduitQuantite> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitQuantite> produits) {
        this.produits = produits;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
