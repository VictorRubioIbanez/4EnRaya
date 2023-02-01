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
    private String userName;
    private String createAt;
    private Player playerOne;
    private Player playerTwo;
    private boolean status;
    private String[][] board = new String[6][7];

}
