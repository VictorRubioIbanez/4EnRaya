package com.example.rayas.player.infraestructure.controller;

import com.example.rayas.player.model.Player;
import com.example.rayas.player.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@RestController
@RequestMapping("/player")
@CrossOrigin
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:62013"})
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerServiceImpl;

    @PostMapping
    public boolean checkPlayer(@RequestBody Player player){
        return playerServiceImpl.checkPlayer(player);
    }

    @GetMapping("/{playerName}")
    public Mono<Player> findPlayerByName(@PathVariable("idPlayer") String idPlayer) {
        return playerServiceImpl.findPlayerByName(idPlayer);
    }

    @GetMapping("/all")
    public Flux<Player> getAllPlayers(){
        return playerServiceImpl.getAllPlayers();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id){
        return playerServiceImpl.deleteUser(id);
    }
}