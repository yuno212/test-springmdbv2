package com.example.springmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends MongoRepository<Paiement, String> {
}
