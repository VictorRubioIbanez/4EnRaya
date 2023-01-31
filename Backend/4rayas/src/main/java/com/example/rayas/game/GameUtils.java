package com.example.rayas.game;

import com.example.rayas.game.model.Game;
import com.example.rayas.player.model.Player;
import lombok.Data;

//intento clase singleton
@Data
public class GameUtils extends Game {

    static final String WINNERPHRASE = "WINNER || Player: ";

    private static GameUtils single_instance = null;
    private static Game game;
    private static String[][] board;
    private GameUtils(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }
    public static GameUtils getInstance(Game game) {
        if (single_instance == null)
            single_instance = new GameUtils(game);
        return single_instance;
    }
    //The player (or just its name) gets passed as a parameter to the
    //function, where the name is used in the array instead of a number
    public static void putToken(Player player, int column) {
        int[] coordinates = new int[2];

        checkColumnSpace(column);

        if (coordinates[0] == 9 && coordinates[1] == 9) {
            System.out.println("There is no space in this column");
        } else {
            board[coordinates[0]][coordinates[1]] = player.getUserName();
        }
    }

    //Shows how the array is displayed in a visual manner
    public static void showActualBoard() {

        for (int i = 0; i < board.length; i++) {

            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < board[i].length; j++){

                System.out.print(board[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    //Checks whether you have an available space or not inside the chosen
    //column
    public static int[] checkColumnSpace(int column) {
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

    public static boolean checkBoard(String playerName) {
        boolean checkBoolean = true;

        if (!checkHorizontal(playerName) || !checkVertical(playerName)) {
            checkBoolean = false;
        }

        return checkBoolean;
    }

    //Checking vertical tokens
    public static boolean checkHorizontal(String playerName) {
        int horizontalCounter = 0;

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == playerName) {
                    horizontalCounter++;
                    if (horizontalCounter >= 4 ) {
                        System.out.println("WINNER in line: " + (i+1) + " || Player: " + playerName);
                        game.setStatus(true);
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
    public static boolean checkVertical(String playerName) {
        int verticalCounter = 0;

        for (int i = 0; i < board[0].length; i++) {

            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == playerName) {
                    verticalCounter++;
                    if (verticalCounter >= 4) {
                        System.out.println("WINNER in column: " + (i+1) + " || Player: " + playerName);
                        game.setStatus(true);
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

    //Fixed Jose Del Rio
    public static boolean checkDiagonal(String playerName) {

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
