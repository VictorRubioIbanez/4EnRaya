package com.example.rayas.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Game")
public class Game {


    @Id
    private String id;

    private String userName;

    private String userName2;

    private String createAt;



}
