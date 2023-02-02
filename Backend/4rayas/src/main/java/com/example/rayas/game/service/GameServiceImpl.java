package com.example.rayas.game.service;

import com.example.rayas.game.infraestructure.repository.GameRepository;
import com.example.rayas.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    GameRepository gameRepository;
    @Override
    public Game createGame(Game game) {

        LocalDateTime ldt = LocalDateTime.now();
        game.setCreateAt(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ITALY).format(ldt));
        game.setId(String.valueOf(new Random().nextInt(1,1000000)));

        return gameRepository.save(game).block();
    }
    @Override
    public void update(Game game){gameRepository.save(game).subscribe();}


    @Override
    public Mono<Game> getGameById(String idGame) {
        return gameRepository.findById(idGame);
    }

    @Override
    public Flux<Game> getGames() {
        return gameRepository.findAll();
    }

    @Override
    public Mono<Void> deleteGame(String id) {
        return gameRepository.deleteById(id);
    }
    @Override
    public Mono<Game> addPlayerTwo(String idGame, String playerTwo) {
        Game game = gameRepository.findById(idGame).block();

        game.setPlayerTwo(playerTwo);


        return gameRepository.save(game);
    }
}
