package com.example.springmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "avis")
public class Avis {
    @Id
    private String id;
    private String idProduit;
    private String idClient;
    private int note;
    private String commentaire;
    private Date dateAvis;

    // Constructors
    public Avis() {}

    public Avis(String idProduit, String idClient, int note, String commentaire, Date dateAvis) {
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.note = note;
        this.commentaire = commentaire;
        this.dateAvis = dateAvis;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(Date dateAvis) {
        this.dateAvis = dateAvis;
    }
}
