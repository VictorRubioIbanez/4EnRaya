package com.example.rayas.game;

import com.example.rayas.game.model.Game;
import com.example.rayas.player.model.Player;
import lombok.Data;

//Clase singleton
//se puede cambiar de tabla usando el SET del GameUtils de Game
@Data
public class GameUtils {

    static final String WINNERPHRASE = "WINNER || Player: ";

    //The player (or just its name) gets passed as a parameter to the
    //function, where the name is used in the array instead of a number
    public static void putToken(int playerNumber, int column, Game game) {
        Integer[][] board = game.getBoard();
        int[] coordinates = new int[2];

        coordinates = checkColumnSpace(column, board);

        if (coordinates[0] == 9 && coordinates[1] == 9) {
            System.out.println("There is no space in this column");
        } else {
            board[coordinates[0]][coordinates[1]] = playerNumber;
            game.setBoard(board);
            if (checkBoard(playerNumber, board)) {
                game.setStatus(true);
            };
        }
    }

    //Shows how the array is displayed in a visual manner
    public static void showActualBoard(Game game) {

        for (int i = game.getBoard().length -1; i >= 0; i--) {

            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < game.getBoard().length; j++){

                System.out.print(game.getBoard()[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    //Checks whether you have an available space or not inside the chosen
    //column
    private static int[] checkColumnSpace(int column, Integer[][] board) {
        int[] coordinates = new int[2];

        coordinates[0]=9;
        coordinates[1]=9;

        for (int i = 0; i < board.length; i++){
            if ( board[i][column] == null ){
                coordinates[0]=i;
                coordinates[1]=column;
                break;
            }
        }

        System.out.println(coordinates[0] + " " + coordinates[1]);
        return coordinates;
    }

    private static boolean checkBoard(int playerNumber,Integer[][] board) {
        boolean checkBoolean = true;

        if (!checkHorizontal(playerNumber, board) || !checkVertical(playerNumber, board) || !checkDiagonal(playerNumber, board)) {
            checkBoolean = false;
        }

        return checkBoolean;
    }

    //Checking vertical tokens
    private static boolean checkHorizontal(int playerNumber, Integer[][] board) {
        int horizontalCounter = 0;

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == playerNumber) {
                    horizontalCounter++;
                    if (horizontalCounter >= 4 ) {
                        System.out.println("WINNER in line: " + (i+1) + " || Player: " + playerNumber);
                        return false;
                    }
                } else if (board[i][j] != null) {
                    horizontalCounter = 0;
                }
            }
            horizontalCounter = 0;
        }

        return true;
    }

    //Checking vertical tokens
    private static boolean checkVertical(int playerNumber, Integer[][] board) {
        int verticalCounter = 0;

        for (int i = 0; i < board[0].length; i++) {

            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == playerNumber) {
                    verticalCounter++;
                    if (verticalCounter >= 4) {
                        System.out.println("WINNER in column: " + (i+1) + " || Player: " + playerNumber);
                        return false;
                    }
                } else if (board[j][i] != null ) {

                    verticalCounter = 0;

                }
            }
            verticalCounter = 0;
        }

        return true;
    }

    //Fixed by Jose Del Rio
    private static boolean checkDiagonal(int playerNumber, Integer[][] board) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == playerNumber &&
                        board[i + 1][j + 1] == playerNumber &&
                        board[i + 2][j + 2] == playerNumber &&
                        board[i + 3][j + 3] == playerNumber ) {
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 4; j--) {
                if (board[i][j] == playerNumber &&
                        board[i + 1][j - 1] == playerNumber &&
                        board[i + 2][j - 2] == playerNumber &&
                        board[i + 3][j - 3] == playerNumber ) {
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == playerNumber &&
                        board[i - 1][j + 1] == playerNumber &&
                        board[i - 2][j + 2] == playerNumber &&
                        board[i - 3][j + 3] == playerNumber ) {
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 6; j > 4; j--) {
                if (board[i][j] == playerNumber &&
                        board[i - 1][j - 1] == playerNumber &&
                        board[i - 2][j - 2] == playerNumber &&
                        board[i - 3][j - 3] == playerNumber ){
                    System.out.println(WINNERPHRASE + playerNumber);
                    return false;
                }
            }
        }    return true;
    }
}
