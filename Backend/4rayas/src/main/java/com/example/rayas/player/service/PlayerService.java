package com.example.rayas.player.service;

import com.example.rayas.player.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {
    Mono<Player> addPlayer(Player player);
    Flux<Player> getAllPlayers();
    Mono<Void> deleteUser(String id);
    boolean checkPlayer(Player player);
}
