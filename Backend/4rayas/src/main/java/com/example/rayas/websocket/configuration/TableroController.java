package com.example.rayas.websocket.configuration;

import com.example.rayas.game.GameUtils;
import com.example.rayas.game.model.Datos;
import com.example.rayas.game.model.Game;
import com.example.rayas.game.service.GameServiceImpl;
import com.example.rayas.player.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableroController {

    @Autowired
    GameServiceImpl gameService;

    @Autowired
    PlayerServiceImpl playerService;



    @MessageMapping("/movimiento")
    @SendTo("/tablero/movimiento")
    public Game movimiento(Datos datos){
        Game game=gameService.getGameById(datos.getIdGame()).block();
        System.out.println(datos.getIdPlayer()+" "+datos.getIdGame()+" "+datos.getColum());
        GameUtils.putToken(game,playerService.findByUserName(datos.getIdPlayer()).block(), datos.getColum());
        gameService.update(game);

        return game;

    }
}
