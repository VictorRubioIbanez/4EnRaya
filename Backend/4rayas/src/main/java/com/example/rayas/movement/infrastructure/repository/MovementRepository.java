package com.example.rayas.movement.infrastructure.repository;


import com.example.rayas.movement.domain.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {
}
