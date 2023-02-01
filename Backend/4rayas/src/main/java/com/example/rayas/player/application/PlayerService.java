package com.example.rayas.player.application;


import com.example.rayas.player.domain.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {

    String createPlayer(Player player);
    Mono<Player> readPlayer(String idPlayer);
    Flux<Player> readAllPlayer();
    Flux<Player> findPlayerByName(String playerName);
    Flux<Player> findPlayerByRank(String playerRank);
    void updatePlayer(Player player);
    void deletePlayer(String idPlayer);
    void deleteAllPlayers();
}
