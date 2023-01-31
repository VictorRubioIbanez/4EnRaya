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
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerServiceImpl;

    @PostMapping
    public boolean checkPlayer(@RequestBody Player player){
        return playerServiceImpl.checkPlayer(player);
    }

}
