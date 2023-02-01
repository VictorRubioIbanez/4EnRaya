package com.example.rayas.player.infraestructure.repository;

import com.example.rayas.player.model.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
    Mono<Player> findByUserName(String userName);
}
