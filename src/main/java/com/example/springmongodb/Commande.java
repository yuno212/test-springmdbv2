package com.example.springmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "commandes")
public class Commande {
    @Id
    private String id;
    private String idUtilisateur;
    private Date dateCommande;
    private String statut;
    private double montantTotal;
    private List<ProduitQuantite> produits;

    // Constructors
    public Commande() {}

    public Commande(String idUtilisateur, Date dateCommande, String statut, double montantTotal, List<ProduitQuantite> produits) {
        this.idUtilisateur = idUtilisateur;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.montantTotal = montantTotal;
        this.produits = produits;
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

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public List<ProduitQuantite> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitQuantite> produits) {
        this.produits = produits;
    }
}
