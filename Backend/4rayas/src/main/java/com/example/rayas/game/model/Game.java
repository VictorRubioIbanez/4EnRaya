package com.example.rayas.game.model;

import com.example.rayas.player.model.Player;
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
    static final String WINNERPHRASE = "WINNER || Player: ";

    @Id
    private String id;
    private String userName;
    private String createAt;
    private Player playerOne;
    private Player playerTwo;
    private boolean status;
    private String[][] board = new String[6][7];

    //The player (or just its name) gets passed as a parameter to the
    //function, where the name is used in the array instead of a number
    public void putToken(Player player, int column) {
        int[] coordinates = new int[2];

        checkColumnSpace(column);

        if (coordinates[0] == 9 && coordinates[1] == 9) {
            System.out.println("There is no space in this column");
        } else {
            this.board[coordinates[0]][coordinates[1]] = player.getUserName();
        }
    }

    //Shows how the array is displayed in a visual manner
    public void showActualBoard() {

        for (int i = 0; i < this.board.length; i++) {

            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < this.board[i].length; j++){

                System.out.print(this.board[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    //Checks whether you have an available space or not inside the chosen
    //column
    int[] checkColumnSpace(int column) {
        int[] coordinates = new int[2];

        coordinates[0]=9;
        coordinates[1]=9;

        for (int i = 0; i < this.board.length; i++){
            if ( this.board[i][column] == null ){
                coordinates[0]=i;
                coordinates[1]=column;
                break;
            }
        }

        System.out.println(coordinates[0] + " " + coordinates[1]);
        return coordinates;
    }

    public boolean checkBoard(String playerName) {
        boolean checkBoolean = true;

        if (!checkHorizontal(playerName) || !checkVertical(playerName)) {
            checkBoolean = false;
        }

        return checkBoolean;
    }

    //Comprobación en horizontal de fichas
    boolean checkHorizontal(String playerName) {
        int horizontalCounter = 0;

        for (int i = 0; i < this.board.length; i++) {

            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[i][j] == playerName) {
                    horizontalCounter++;
                    if (horizontalCounter >= 4 ) {
                        System.out.println("WINNER in line: " + (i+1) + " || Player: " + playerName);
                        this.status = true;
                        return false;
                    }
                } else if (this.board[i][j] != null) {
                    horizontalCounter = 0;
                }
            }
            horizontalCounter = 0;
        }

        return true;
    }

    //Comprobación en vertical de fichas
    boolean checkVertical(String playerName) {
        int verticalCounter = 0;

        for (int i = 0; i < this.board[0].length; i++) {

            for (int j = 0; j < this.board.length; j++) {
                if (this.board[j][i] == playerName) {
                    verticalCounter++;
                    if (verticalCounter >= 4) {
                        System.out.println("WINNER in column: " + (i+1) + " || Player: " + playerName);
                        this.status = true;
                        return false;
                    }
                } else if (this.board[j][i] != null ) {

                    verticalCounter = 0;

                }
            }
            verticalCounter = 0;
        }

        return true;
    }

    //Arreglado por Jose Del Rio
    boolean checkDiagonal(String playerName) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].contains(playerName) &&
                        board[i + 1][j + 1].contains(playerName) &&
                        board[i + 2][j + 2].contains(playerName) &&
                        board[i + 3][j + 3].contains(playerName)) {
                    System.out.println(WINNERPHRASE + playerName);
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 4; j--) {
                if (board[i][j].contains(playerName) &&
                        board[i + 1][j - 1].contains(playerName) &&
                        board[i + 2][j - 2].contains(playerName) &&
                        board[i + 3][j - 3].contains(playerName)) {
                    System.out.println(WINNERPHRASE + playerName);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].contains(playerName) &&
                        board[i - 1][j + 1].contains(playerName) &&
                        board[i - 2][j + 2].contains(playerName) &&
                        board[i - 3][j + 3].contains(playerName)) {
                    System.out.println(WINNERPHRASE + playerName);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 6; j > 4; j--) {
                if (board[i][j].contains(playerName) &&
                        board[i - 1][j - 1].contains(playerName) &&
                        board[i - 2][j - 2].contains(playerName) &&
                        board[i - 3][j - 3].contains(playerName)) {
                    System.out.println(WINNERPHRASE + playerName);
                    return false;
                }
            }
        }    return true;
    }

}
