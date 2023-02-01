package com.example.rayas.game.service;

import com.example.rayas.game.infraestructure.repository.GameRepository;
import com.example.rayas.game.model.Game;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    GameRepository gameRepository;
    @Override
    public Game createGame(Game game) {

        game.setCreateAt(new Date().toString());
        game.setId(String.valueOf(new Random().nextInt(1,100)));

        return gameRepository.save(game).block();
    }

    @Override
    public Mono<Game> getGame(String id){ return gameRepository.findById(id);}

    @Override
    public Flux<Game> getGames() {
        return gameRepository.findAll();
    }

    @Override
    public Mono<Void> deleteGame(String id) {
        return gameRepository.deleteById(id);
    }
}
