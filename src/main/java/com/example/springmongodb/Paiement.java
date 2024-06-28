package com.example.springmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "paiements")
public class Paiement {
    @Id
    private String id;
    private String idCommande;
    private Date datePaiement;
    private double montant;
    private String methodePaiement;

    // Constructors
    public Paiement() {}

    public Paiement(String idCommande, Date datePaiement, double montant, String methodePaiement) {
        this.idCommande = idCommande;
        this.datePaiement = datePaiement;
        this.montant = montant;
        this.methodePaiement = methodePaiement;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }
}
