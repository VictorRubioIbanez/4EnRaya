package com.example.rayas.movement.application;


import com.example.rayas.movement.domain.Movement;
import com.example.rayas.movement.infrastructure.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementServiceImpl implements MovementService{

    @Autowired
    MovementRepository movementRepository;

    @Override
    public String createMovement(Movement movement) {
        movementRepository.save(movement).subscribe();
        return "Movement by " + movement.getPlayer() + " in column " + movement.getColumn() + " has been saved";
    }

    @Override
    public Mono<Movement> readMovement(String idMovement) {
        return movementRepository.findById(idMovement);
    }

    @Override
    public Flux<Movement> readAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public void updateMovement(Movement movement) {
        movementRepository.save(movement).subscribe();
    }

    @Override
    public void deleteMovement(String idMovement) {
        movementRepository.deleteById(idMovement).subscribe();
    }
}
