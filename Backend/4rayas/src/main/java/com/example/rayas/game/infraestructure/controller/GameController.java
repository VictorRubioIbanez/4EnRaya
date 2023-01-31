package com.example.rayas.game.infraestructure.controller;

import com.example.rayas.game.model.Game;
import com.example.rayas.game.service.GameService;

import com.example.rayas.game.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    @Autowired
    GameServiceImpl gameService;

    @PostMapping
    public Game createGame(@RequestBody Game game){
        return gameService.createGame(game);
    }

    @GetMapping("/{idGame}")
    public Mono<Game> getGameById(@PathVariable("idGame") String idGame){
        return gameService.getGameById(idGame);
    }

    @GetMapping
    public Flux<Game> getAllGames(){
        return gameService.getGames();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id){
        return gameService.deleteGame(id);
    }
}

