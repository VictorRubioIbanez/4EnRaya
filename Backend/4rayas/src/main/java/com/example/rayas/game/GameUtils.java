package com.example.rayas.game;

import com.example.rayas.game.model.Game;
import com.example.rayas.player.model.Player;
import lombok.Data;

//Clase singleton
//se puede cambiar de tabla usando el SET del GameUtils de Game
@Data
public class GameUtils {



    static int[] coordinates=new int[2];

    //The player (or just its name) gets passed as a parameter to the
    //function, where the name is used in the array instead of a number
    public static boolean putToken(Game game,Player player, int column) {
           checkColumnSpace(game,column);


        if (coordinates[0] == 9 && coordinates[1] == 9) {
            System.out.println("There is no space in this column");
        } else {

            game.getBoard()[coordinates[0]][coordinates[1]] = player.getUserName();


        }
        return checkBoard(game,player.getUserName());
    }

    //Shows how the array is displayed in a visual manner
    public static void showActualBoard(Game game) {


        for (int i = 0; i < game.getBoard().length; i++) {

            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < game.getBoard()[i].length; j++){


                System.out.print(game.getBoard()[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    //Checks whether you have an available space or not inside the chosen
    //column

    public static void checkColumnSpace(Game game,int column) {



        coordinates[0]=9;
        coordinates[1]=9;

        for (int i =  game.getBoard().length - 1;i>=0 ;i--){
            if ( game.getBoard()[i][column].equals("0")  ){
                coordinates[0]=i;
                coordinates[1]=column;
                break;
            }
        }

        System.out.println(coordinates[0] + " " + coordinates[1]);

    }


    public static boolean checkBoard(Game game,String playerName) {
        boolean checkBoolean = true;

        if (!checkHorizontal(game,playerName) || !checkVertical(game,playerName) || !checkDiagonal(game,playerName)) {

            checkBoolean = false;
        }

        return checkBoolean;
    }

    //Checking vertical tokens

    private static boolean checkHorizontal(Game game,String playerName) {

        int horizontalCounter = 0;

        for (int i = 0; i < game.getBoard().length; i++) {


            for (int j = 0; j < game.getBoard()[0].length; j++) {
                if (game.getBoard()[i][j].equals(playerName)) {
                    horizontalCounter++;
                    if (horizontalCounter >= 4 ) {
                        game.setStatus(true);
                        System.out.println("WINNER in line: " + (i+1) + " || Player: " + playerName);
                        game.setStatus(true);

                        return false;
                    }
                } else if (!game.getBoard()[i][j].equals("0")) {
                    horizontalCounter = 0;
                }
            }
            horizontalCounter = 0;
        }

        return true;
    }

    //Checking vertical tokens

    private static boolean checkVertical(Game game,String playerName) {

        int verticalCounter = 0;

        for (int i = 0; i < game.getBoard()[0].length; i++) {


            for (int j = 0; j < game.getBoard().length; j++) {
                if (game.getBoard()[j][i].equals(playerName)) {
                    verticalCounter++;
                    if (verticalCounter >= 4) {
                        game.setStatus(true);
                        System.out.println("WINNER in column: " + (i+1) + " || Player: " + playerName);
                        game.setStatus(true);

                        return false;
                    }
                } else if (!game.getBoard()[j][i].equals("0") ) {

                    verticalCounter = 0;

                }
            }
            verticalCounter = 0;
        }

        return true;
    }

    //Fixed by Jose Del Rio

    private static boolean checkDiagonal(Game game,String playerName) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (game.getBoard()[i][j].contains(playerName) &&
                        game.getBoard()[i + 1][j + 1].contains(playerName) &&
                        game.getBoard()[i + 2][j + 2].contains(playerName) &&
                        game.getBoard()[i + 3][j + 3].contains(playerName)) {
                    game.setStatus(true);
                    System.out.println( "WINNER || Player: " + playerName);

                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 4; j--) {

                if (game.getBoard()[i][j].contains(playerName) &&
                        game.getBoard()[i + 1][j - 1].contains(playerName) &&
                        game.getBoard()[i + 2][j - 2].contains(playerName) &&
                        game.getBoard()[i + 3][j - 3].contains(playerName)) {
                    game.setStatus(true);
                    System.out.println( "WINNER || Player: " + playerName);

                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 0; j < 4; j++) {

                if (game.getBoard()[i][j].contains(playerName) &&
                        game.getBoard()[i - 1][j + 1].contains(playerName) &&
                        game.getBoard()[i - 2][j + 2].contains(playerName) &&
                        game.getBoard()[i - 3][j + 3].contains(playerName)) {
                    game.setStatus(true);
                    System.out.println( "WINNER || Player: " + playerName);

                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 6; j > 4; j--) {

                if (game.getBoard()[i][j].contains(playerName) &&
                        game.getBoard()[i - 1][j - 1].contains(playerName) &&
                        game.getBoard()[i - 2][j - 2].contains(playerName) &&
                        game.getBoard()[i - 3][j - 3].contains(playerName)) {
                    game.setStatus(true);
                    System.out.println( "WINNER || Player: " + playerName);

                    return false;
                }
            }
        }    return true;
    }
}
