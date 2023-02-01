package com.example.rayas.board.domain;



import com.example.rayas.movement.domain.Movement;
import com.example.rayas.player.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Board implements Serializable {

    public Board() {
        this.isFinished = false;
    }

    @Id
    private String idBoard;
    private String boardName;


    private Integer[][] gameBoard = new Integer[6][7];
    private ArrayList<Movement> moves = new ArrayList<Movement>();
    private Player playerOne;
    private Player playerTwo;
    private boolean isFinished;

    public Integer[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Integer[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public ArrayList<Movement> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Movement> moves) {
        this.moves = moves;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
    public String obtainSize() {
        return "Array Size is: HEIGHT: " + this.gameBoard.length + " and LENGTH: " + this.gameBoard[0].length;
    }

    //Introduce la ficha dentro del tablero
    public void putCoin(int column, Player player, int playerNumber) {
        int[] coordinates = new int[2];
        column -= 1;

        Movement playerMovement = new Movement( column, player);

        checkColumnSpace(column);

        if (coordinates[0] == 9 && coordinates[1] == 9) {
            System.out.println("There is no space in this column");
        } else {
            this.gameBoard[coordinates[0]][coordinates[1]] = playerNumber;
            this.moves.add(playerMovement);
        }

    }



    //Muestra el estado actual del tablero
    public void showActualBoard() {

        for (int i = 0; i < this.gameBoard.length; i++) {

            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < this.gameBoard[i].length; j++){

                System.out.print(this.gameBoard[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    //Muestra el histórico de movimientos del tablero y el jugador asociado a ellos
    public void showHistoryMoves() {
        int counter = 1;
        for ( Movement move : this.moves) {
            System.out.println("Move " + counter);
            System.out.println("Player: " + move.getPlayer());
            System.out.println("Selected column: " + move.getColumn() + "\n");
            counter++;
        }
    }
    //Comprueba que si algún jugador tiene 4 fichas alineadas
    public boolean checkBoard(int playerNumber) {
        boolean checkBoolean = true;

        if (!checkHorizontal(playerNumber) || !checkVertical(playerNumber)) {
            checkBoolean = false;
        }

        return checkBoolean;
    }

    //Comprobación en horizontal de fichas
    boolean checkHorizontal(int playerNumber) {
        int horizontalCounter = 0;

        for (int i = 0; i < this.gameBoard.length; i++) {

            for (int j = 0; j < this.gameBoard[0].length; j++) {
                if (this.gameBoard[i][j] == playerNumber) {
                    horizontalCounter++;
                    if (horizontalCounter >= 4 ) {
                        System.out.println("WINNER in line: " + (i+1) + " || PLayer: " + playerNumber);
                        this.isFinished = true;
                        return false;
                    }
                } else if (this.gameBoard[i][j] != null) {
                    horizontalCounter = 0;
                }
            }
            horizontalCounter = 0;
        }

        return true;
    }

    //Comprobación en vertical de fichas
    boolean checkVertical(int playerNumber) {
        int verticalCounter = 0;

        for (int i = 0; i < this.gameBoard[0].length; i++) {

            for (int j = 0; j < this.gameBoard.length; j++) {
                if (this.gameBoard[j][i] == playerNumber) {
                    verticalCounter++;
                    if (verticalCounter >= 4) {
                        System.out.println("WINNER in column: " + (i+1) + " || Player: " + playerNumber);
                        this.isFinished = true;
                        return false;
                    }
                } else if (this.gameBoard[j][i] != null ) {

                    verticalCounter = 0;

                }
            }
            verticalCounter = 0;
        }

        return true;
    }

    //Arreglado por Jose Del Rio
    boolean checkDiagonal(int playerNumber) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == playerNumber &&
                        gameBoard[i + 1][j + 1] == playerNumber &&
                        gameBoard[i + 2][j + 2] == playerNumber &&
                        gameBoard[i + 3][j + 3] == playerNumber) {
                    System.out.println("WINNER || Player: " + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 4; j--) {
                if (gameBoard[i][j] == playerNumber &&
                        gameBoard[i + 1][j - 1] == playerNumber &&
                        gameBoard[i + 2][j - 2] == playerNumber &&
                        gameBoard[i + 3][j - 3] == playerNumber) {
                    System.out.println("WINNER || Player: " + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == playerNumber &&
                        gameBoard[i - 1][j + 1] == playerNumber &&
                        gameBoard[i - 2][j + 2] == playerNumber &&
                        gameBoard[i - 3][j + 3] == playerNumber) {
                    System.out.println("WINNER || Player: " + playerNumber);
                    return false;
                }
            }
        }

        for (int i = 5; i > 3; i--) {
            for (int j = 6; j > 4; j--) {
                if (gameBoard[i][j] == playerNumber &&
                        gameBoard[i - 1][j - 1] == playerNumber &&
                        gameBoard[i - 2][j - 2] == playerNumber &&
                        gameBoard[i - 3][j - 3] == playerNumber) {
                    System.out.println("WINNER || Player: " + playerNumber);
                    return false;
                }
            }
        }    return true;
    }

    //Comprueba si hay hueco en la columna
    //Arreglado por Jose Del Rio
    int[] checkColumnSpace(int column) {
        int coordinates[] = new int[2];

        coordinates[0]=9;
        coordinates[1]=9;

        for (int i = 0; i < this.gameBoard.length; i++){
            if ( this.gameBoard[i][column] == 0){
                coordinates[0]=i;
                coordinates[1]=column;
                break;
            }
        }

        System.out.println(coordinates[0] + " " + coordinates[1]);
        return coordinates;
    }
}