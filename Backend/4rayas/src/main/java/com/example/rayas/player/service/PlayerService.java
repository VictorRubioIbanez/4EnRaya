package com.example.rayas.player.service;

import com.example.rayas.player.model.Player;
import reactor.core.publisher.Mono;

public interface PlayerService {
    boolean checkPlayer(Player player);

    Mono<Player> findByUserName(String userName);
}
