package com.example.rayas.movement.domain;



import com.example.rayas.player.domain.Player;

import java.io.Serializable;

public class Movement implements Serializable {

    public Movement(int column, Player player) {
        this.column = column;
        this.player = player.getName();
    }
    private int column;

    private String player;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
