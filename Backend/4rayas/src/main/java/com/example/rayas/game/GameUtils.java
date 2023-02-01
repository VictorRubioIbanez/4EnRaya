package com.example.rayas.game;

import com.example.rayas.game.model.Game;
import com.example.rayas.player.model.Player;
import lombok.Data;

//Clase singleton
//se puede cambiar de tabla usando el SET del GameUtils de Game
@Data
public class GameUtils {

    static final String WINNERPHRASE = "WINNER || Player: ";

    private Game game;
    private int playerNumber;
    private int column;

    GameUtils(Game game, int playerNumber, int column) {
        this.game = game;
        this.playerNumber = playerNumber;
        this.column = column;
    }

    //The player (or just its name) gets passed as a parameter to the
    //function, where the name is used in the array instead of a number
    public void putToken(int playerNumber, int column, Game game) {
        Integer[][] board = game.getBoard();
        int[] coordinates = new int[2];

        coordinates = checkColumnSpace(column);

        if (coordinates[0] == 9 && coordinates[1] == 9) {
            System.out.println("There is no space in this column");
        } else {
            board[coordinates[0]][coordinates[1]] = playerNumber;
            game.setBoard(board);
            checkBoard(playerNumber);
        }
    }

    //Shows how the array is displayed in a visual manner
    public void showActualBoard() {

        for (int i = 0; i < this.game.getBoard().length; i++) {

            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < this.game.getBoard().length; j++){

                System.out.print(this.game.getBoard()[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    //Checks whether you have an available space or not inside the chosen
    //column
    private int[] checkColumnSpace(int column) {
        int[] coordinates = new int[2];

        coordinates[0]=9;
        coordinates[1]=9;

        for (int i = 0; i < this.game.getBoard().length; i++){
            if ( this.game.getBoard()[i][column] == null ){
                coordinates[0]=i;
                coordinates[1]=column;
                break;
            }
        }

        System.out.println(coordinates[0] + " " + coordinates[1]);
        return coordinates;
    }

    private boolean checkBoard(int playerNumber) {
        boolean checkBoolean = true;

        if (!checkHorizontal(playerNumber) || !checkVertical(playerNumber) || !checkDiagonal(playerNumber)) {
            checkBoolean = false;
        }

        return checkBoolean;
    }

    //Checking vertical tokens
    private boolean checkHorizontal(int playerNumber) {
        int horizontalCounter = 0;

        for (int i = 0; i < this.game.getBoard().length; i++) {

            for (int j = 0; j < this.game.getBoard().length; j++) {
                if (this.game.getBoard()[i][j] == playerNumber) {
                    horizontalCounter++;
                    if (horizontalCounter >= 4 ) {
                        System.out.println("WINNER in line: " + (i+1) + " || Player: " + playerNumber);
                        game.setStatus(true);
                        return false;
                    }
                } else if (this.game.getBoard()[i][j] != null) {
                    horizontalCounter = 0;
                }
            }
            horizontalCounter = 0;
        }

        return true;
    }

    //Checking vertical tokens
    private boolean checkVertical(int playerNumber) {
        int verticalCounter = 0;

        for (int i = 0; i < this.game.getBoard()[0].length; i++) {

            for (int j = 0; j < this.game.getBoard().length; j++) {
                if (this.game.getBoard()[j][i] == playerNumber) {
                    verticalCounter++;
                    if (verticalCounter >= 4) {
                        System.out.println("WINNER in column: " + (i+1) + " || Player: " + playerNumber);
                        game.setStatus(true);
                        return false;
                    }
                } else if (this.game.getBoard()[j][i] != null ) {

                    verticalCounter = 0;

                }
            }
            verticalCounter = 0;
        }

        return true;
    }

    //Fixed by Jose Del Rio
    private boolean checkDiagonal(int playerNumber) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.game.getBoard()[i][j] == playerNumber &&
                        this.game.getBoard()[i + 1][j + 1] == playerNumber &&
                        this.game.getBoard()[i + 2][j + 2] == playerNumber &&
                        this.game.getBoard()[i + 3][j + 3] == playerNumber ) {
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 4; j--) {
                if (this.game.getBoard()[i][j] == playerNumber &&
                        this.game.getBoard()[i + 1][j - 1] == playerNumber &&
                        this.game.getBoard()[i + 2][j - 2] == playerNumber &&
                        this.game.getBoard()[i + 3][j - 3] == playerNumber ) {
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 0; j < 4; j++) {
                if (this.game.getBoard()[i][j] == playerNumber &&
                        this.game.getBoard()[i - 1][j + 1] == playerNumber &&
                        this.game.getBoard()[i - 2][j + 2] == playerNumber &&
                        this.game.getBoard()[i - 3][j + 3] == playerNumber ) {
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 6; j > 4; j--) {
                if (this.game.getBoard()[i][j] == playerNumber &&
                        this.game.getBoard()[i - 1][j - 1] == playerNumber &&
                        this.game.getBoard()[i - 2][j - 2] == playerNumber &&
                        this.game.getBoard()[i - 3][j - 3] == playerNumber ){
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }    return true;
    }
}
