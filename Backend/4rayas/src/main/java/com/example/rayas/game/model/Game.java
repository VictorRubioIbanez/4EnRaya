package com.example.rayas.game.model;

import com.example.rayas.player.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Game")
public class Game {


    @Id
    private String id;
    private String createAt;
    private String playerOne;
    private String playerTwo;
    private boolean status;
    private Integer[][] board = new Integer[6][7];

}
