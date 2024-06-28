from faker import Faker
from pymongo import MongoClient
import random
import json

# Connexion à la base de données MongoDB
client = MongoClient('mongodb://localhost:27017/')
db = client['ecommercev2']

fake = Faker()

# Fonctions pour générer des données fictives
def create_utilisateur():
    return {
        "idUtilisateur": fake.unique.random_int(),
        "nom": fake.name(),
        "email": fake.unique.email(),
        "adresse": fake.address() if random.choice([True, False]) else None
    }

def create_produit():
    return {
        "idProduit": fake.unique.random_int(),
        "nomProduit": fake.word(),
        "description": fake.text(),
        "prix": round(random.uniform(10.0, 1000.0), 2),
        "categorie": fake.word(),
        "stock": fake.random_int(min=1, max=100)
    }

def create_commande(utilisateur_ids):
    return {
        "idCommande": fake.unique.random_int(),
        "dateCommande": fake.date_time_this_year(),
        "statut": random.choice(["En attente", "Expédiée", "Livré"]),
        "idUtilisateur": random.choice(utilisateur_ids),
        "montantTotal": round(random.uniform(10.0, 1000.0), 2)
    }

def create_panier(utilisateur_ids):
    return {
        "idPanier": fake.unique.random_int(),
        "idUtilisateur": random.choice(utilisateur_ids),
        "dateCreation": fake.date_time_this_year()
    }

def create_lignepanier(panier_ids, produit_ids, max_produits_par_panier=5):
    lignespanier = []
    for panier_id in panier_ids:
        num_produits = random.randint(1, max_produits_par_panier)
        produits_choisis = random.sample(produit_ids, num_produits)
        for produit_id in produits_choisis:
            lignespanier.append({
                "idLignePanier": fake.unique.random_int(),
                "idPanier": panier_id,
                "idProduit": produit_id,
                "Quantité": fake.random_int(min=1, max=10)
            })
    return lignespanier

def create_paiement(commande_ids):
    return {
        "idPaiement": fake.unique.random_int(),
        "idCommande": random.choice(commande_ids),
        "dateDePaiement": fake.date_time_this_year(),
        "montant": round(random.uniform(10.0, 1000.0), 2),
        "methodeDePaiement": random.choice(["Carte de credit", "PayPal", "Virement", "Carte Cadeau"])
    }

def create_avis(utilisateur_ids, produit_ids):
    return {
        "idAvis": fake.unique.random_int(),
        "idProduit": random.choice(produit_ids),
        "idUtilisateur": random.choice(utilisateur_ids),
        "note": random.randint(1, 5),
        "commentaire": fake.sentence() if random.choice([True, False]) else None,
        "dateAvis": fake.date_time_this_year()
    }

# Nombre de documents par collection
num_utilisateurs = 1000
num_produits = 250
num_commandes = 2000
num_paniers = 900
num_lignepaniers = 1350
num_paiements = 1200
num_avis = 800

# Génération des données
utilisateurs = [create_utilisateur() for _ in range(num_utilisateurs)]
produits = [create_produit() for _ in range(num_produits)]
utilisateur_ids = [u['idUtilisateur'] for u in utilisateurs]
produit_ids = [p['idProduit'] for p in produits]
commandes = [create_commande(utilisateur_ids) for _ in range(num_commandes)]
commande_ids = [c['idCommande'] for c in commandes]
paniers = [create_panier(utilisateur_ids) for _ in range(num_paniers)]
panier_ids = [p['idPanier'] for p in paniers]
lignepaniers = create_lignepanier(panier_ids, produit_ids, max_produits_par_panier=5)
paiements = [create_paiement(commande_ids) for _ in range(num_paiements)]
avis = [create_avis(utilisateur_ids, produit_ids) for _ in range(num_avis)]

# Insertion des données dans MongoDB
db.Utilisateur.insert_many(utilisateurs)
db.Produit.insert_many(produits)
db.Commande.insert_many(commandes)
db.Panier.insert_many(paniers)
db.LignePanier.insert_many(lignepaniers)
db.Paiement.insert_many(paiements)
db.Avis.insert_many(avis)