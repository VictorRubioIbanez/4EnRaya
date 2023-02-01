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
    private String userName;



    private String createAt;
    private String userName2;

}
