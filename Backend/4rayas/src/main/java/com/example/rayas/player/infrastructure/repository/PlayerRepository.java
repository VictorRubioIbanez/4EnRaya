package com.example.rayas.player.infrastructure.repository;


import com.example.rayas.player.domain.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
    Flux<Player> findPlayerByName(String name);
    Flux<Player> findPlayerByRank(String rank);
}
