package com.example.rayas.game.service;

import com.example.rayas.game.model.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {
<<<<<<< HEAD
     Game createGame(Game game);
     Flux<Game> getGames();
     Mono<Void> deleteGame (String id);

     Mono<Game> getGame(String id);

=======
    public Game createGame(Game game);
    public Flux<Game> getGames();
    public Mono<Void> deleteGame (String id);
    public Mono<Game> addPlayerTwo(String idGame, String playerTwo);
>>>>>>> andres

}
