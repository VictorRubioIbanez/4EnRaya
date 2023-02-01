package com.example.rayas.player.domain;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Player implements Serializable {

    public Player() {}
    @Id
    private String id;
    @NonNull
    private String name;

    @Nullable
    //Posible implementación
    private String rank;


}
