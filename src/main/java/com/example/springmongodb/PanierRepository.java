package com.example.springmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PanierRepository extends MongoRepository<Panier, String> {
}
