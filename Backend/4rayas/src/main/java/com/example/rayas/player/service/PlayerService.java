package com.example.rayas.player.service;

import com.example.rayas.player.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {
    Mono<Player> addPlayer(Player player);
    Mono<Player> findPlayerByName(String playerName);
    Flux<Player> getAllPlayers();
    Mono<Void> deleteUser(String idPlayer);
    boolean checkPlayer(Player player);
}
