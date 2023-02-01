package com.example.rayas.movement.application;


import com.example.rayas.movement.domain.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {

    String createMovement(Movement movement);
    Mono<Movement> readMovement(String idMovement);
    Flux<Movement> readAllMovements();
    void updateMovement(Movement movement);
    void deleteMovement(String idMovement);
}
