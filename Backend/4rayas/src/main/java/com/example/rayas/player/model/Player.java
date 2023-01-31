package com.example.rayas.player.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Players")
public class Player {

    @Id
    private String idPlayer;

    private String userName;

    private String password;

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
