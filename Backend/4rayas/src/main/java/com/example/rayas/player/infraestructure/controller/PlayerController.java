package com.example.rayas.player.infraestructure.controller;

import com.example.rayas.player.model.Player;
import com.example.rayas.player.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerServiceImpl;

    @PostMapping
    public boolean checkPlayer(@RequestBody Player player){
        return playerServiceImpl.checkPlayer(player);
    }

}
