package com.example.rayas.game.service;

import com.example.rayas.game.model.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {
    public Game createGame(Game game);

    void update(Game game);

    public Mono<Game> getGameById(String idGame);
    public Flux<Game> getGames();
    public Mono<Void> deleteGame (String id);
    public Mono<Game> addPlayerTwo(String idGame, String playerTwo);

}
