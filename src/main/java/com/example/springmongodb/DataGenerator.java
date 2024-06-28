package com.example.springmongodb;

import com.github.javafaker.Faker;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static void main(String[] args) {
        try (var client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase db = client.getDatabase("ecommerce2");

            List<Document> utilisateurs = generateUtilisateurs(1000);
            List<Document> produits = generateProduits(250);
            List<Integer> utilisateurIds = utilisateurs.stream().map(doc -> doc.getInteger("idUtilisateur")).toList();
            List<Integer> produitIds = produits.stream().map(doc -> doc.getInteger("idProduit")).toList();

            List<Document> commandes = generateCommandes(2000, utilisateurIds);
            List<Integer> commandeIds = commandes.stream().map(doc -> doc.getInteger("idCommande")).toList();
            List<Document> paniers = generatePaniers(900, utilisateurIds);
            List<Integer> panierIds = paniers.stream().map(doc -> doc.getInteger("idPanier")).toList();

            List<Document> lignePaniers = generateLignePaniers(1350, panierIds, produitIds);
            List<Document> paiements = generatePaiements(1200, commandeIds);
            List<Document> avis = generateAvis(800, utilisateurIds, produitIds);

            db.getCollection("Utilisateur").insertMany(utilisateurs);
            db.getCollection("Produit").insertMany(produits);
            db.getCollection("Commande").insertMany(commandes);
            db.getCollection("Panier").insertMany(paniers);
            db.getCollection("LignePanier").insertMany(lignePaniers);
            db.getCollection("Paiement").insertMany(paiements);
            db.getCollection("Avis").insertMany(avis);
        }
    }

    private static List<Document> generateUtilisateurs(int count) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idUtilisateur", faker.random().nextInt(1, 10000))
                .append("nom", faker.name().fullName())
                .append("email", faker.internet().emailAddress())
                .append("adresse", faker.bool().bool() ? faker.address().fullAddress() : null));
        }
        return list;
    }

    private static List<Document> generateProduits(int count) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idProduit", faker.random().nextInt(1, 10000))
                .append("nomProduit", faker.commerce().productName())
                .append("description", faker.lorem().sentence())
                .append("prix", faker.number().randomDouble(2, 10, 1000))
                .append("categorie", faker.commerce().department())
                .append("stock", faker.number().numberBetween(1, 100)));
        }
        return list;
    }

    private static List<Document> generateCommandes(int count, List<Integer> utilisateurIds) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idCommande", faker.random().nextInt(1, 10000))
                .append("dateCommande", faker.date().birthday())
                .append("statut", faker.options().option("En attente", "Expédiée", "Livré"))
                .append("idUtilisateur", utilisateurIds.get(random.nextInt(utilisateurIds.size())))
                .append("montantTotal", faker.number().randomDouble(2, 10, 1000)));
        }
        return list;
    }

    private static List<Document> generatePaniers(int count, List<Integer> utilisateurIds) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idPanier", faker.random().nextInt(1, 10000))
                .append("idUtilisateur", utilisateurIds.get(random.nextInt(utilisateurIds.size())))
                .append("dateCreation", faker.date().birthday()));
        }
        return list;
    }

    private static List<Document> generateLignePaniers(int count, List<Integer> panierIds, List<Integer> produitIds) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idLignePanier", faker.random().nextInt(1, 10000))
                .append("idPanier", panierIds.get(random.nextInt(panierIds.size())))
                .append("idProduit", produitIds.get(random.nextInt(produitIds.size())))
                .append("Quantité", faker.number().numberBetween(1, 10)));
        }
        return list;
    }

    private static List<Document> generatePaiements(int count, List<Integer> commandeIds) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idPaiement", faker.random().nextInt(1, 10000))
                .append("idCommande", commandeIds.get(random.nextInt(commandeIds.size())))
                .append("dateDePaiement", faker.date().birthday())
                .append("montant", faker.number().randomDouble(2, 10, 1000))
                .append("methodeDePaiement", faker.options().option("Carte de credit", "PayPal", "Virement", "Carte Cadeau")));
        }
        return list;
    }

    private static List<Document> generateAvis(int count, List<Integer> utilisateurIds, List<Integer> produitIds) {
        List<Document> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Document("idAvis", faker.random().nextInt(1, 10000))
                .append("idProduit", produitIds.get(random.nextInt(produitIds.size())))
                .append("idUtilisateur", utilisateurIds.get(random.nextInt(utilisateurIds.size())))
                .append("note", faker.number().numberBetween(1, 5))
                .append("commentaire", faker.bool().bool() ? faker.lorem().sentence() : null)
                .append("dateAvis", faker.date().birthday()));
        }
        return list;
    }
}