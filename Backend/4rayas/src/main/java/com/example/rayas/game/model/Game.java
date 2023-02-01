package com.example.rayas.game.model;

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
<<<<<<< HEAD
    private String userName;
=======

    private String playerOne;

    private String playerTwo;
>>>>>>> andres



    private String createAt;
<<<<<<< HEAD
    private String userName2;

=======

    private boolean status;

    private String[][] gameBoard = new String[6][7];
>>>>>>> andres
}
