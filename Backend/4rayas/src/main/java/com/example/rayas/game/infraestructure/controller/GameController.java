package com.example.rayas.game.infraestructure.controller;

import com.example.rayas.game.GameUtils;
import com.example.rayas.game.model.Game;
import com.example.rayas.game.service.GameService;

import com.example.rayas.game.service.GameServiceImpl;
import com.example.rayas.player.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    @Autowired
    GameServiceImpl gameService;

    @Autowired
    PlayerServiceImpl playerService;



//    @PostMapping("/{colum}/{idPlayer}/{idGame}")
//    public boolean movimiento(@PathVariable int colum, @PathVariable String idPlayer,@PathVariable String idGame){
//        Game game=gameService.getGameById(idGame).block();
//        System.out.println(idPlayer+ idGame);
//        GameUtils.showActualBoard(game);
//       boolean res= GameUtils.putToken(game,playerService.findByUserName(idPlayer).block(),colum);
//        gameService.update(game);
//       GameUtils.showActualBoard(game);
//       return res;
//
//    }

    @PostMapping
    public Game createGame(@RequestBody Game game){
        return gameService.createGame(game);
    }

    @GetMapping("/{idGame}")
    public Mono<Game> getGameById(@PathVariable("idGame") String idGame){
        return gameService.getGameById(idGame);
    }

    @GetMapping("/all")
    public Flux<Game> getAllGames(){
        return gameService.getGames();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id){
        return gameService.deleteGame(id);
    }

    @PostMapping("/playerTwo/{idGame}/{playerTwo}")
    public Mono<Game> setPlayerTwo(@PathVariable String idGame,
                                   @PathVariable String playerTwo){
        return gameService.addPlayerTwo(idGame,playerTwo);
    }

}

