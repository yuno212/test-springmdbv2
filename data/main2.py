import os
import json
import requests

# Constants
BASE_URL = "http://localhost:8080/api"
DATA_DIR = ""

# Mapping of filenames to endpoints
file_to_endpoint = {
    "Avis.json": "avis",
    "Commande.json": "commandes",
    "LignePanier.json": "lignepanier",
    "Paiement.json": "paiements",
    "Panier.json": "paniers",
    "Produit.json": "produits",
    "Utilisateur.json": "utilisateurs"
}

def post_json(url, json_data):
    headers = {'Content-Type': 'application/json'}
    response = requests.post(url, headers=headers, data=json.dumps(json_data))
    
    if response.status_code in [200, 201]:
        print(f"Successfully posted to {url}")
    else:
        print(f"Failed to post to {url}. Status code: {response.status_code}. Response: {response.text}")

def main():
    for filename, endpoint in file_to_endpoint.items():
        filepath = os.path.join(DATA_DIR, filename)
        
        if os.path.exists(filepath):
            with open(filepath, 'r', encoding='utf-8') as file:
                try:
                    json_data = json.load(file)
                    url = f"{BASE_URL}/{endpoint}"
                    post_json(url, json_data)
                except json.JSONDecodeError as e:
                    print(f"Error decoding JSON from file {filepath}: {e}")
        else:
            print(f"File {filepath} does not exist")

if __name__ == "__main__":
    main()
