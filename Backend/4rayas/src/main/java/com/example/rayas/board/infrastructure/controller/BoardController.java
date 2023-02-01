package com.example.rayas.board.infrastructure.controller;


import com.example.rayas.board.application.BoardServiceImpl;
import com.example.rayas.board.domain.Board;
import com.example.rayas.game.model.Game;
import com.example.rayas.movement.application.MovementServiceImpl;
import com.example.rayas.movement.domain.Movement;
import com.example.rayas.player.application.PlayerServiceImpl;
import com.example.rayas.player.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:4200")
public class BoardController {

    @Autowired
    PlayerServiceImpl playerService;

    @Autowired
    MovementServiceImpl movementService;

    @Autowired
    BoardServiceImpl boardService;

    //Player part
    @PostMapping("/player")
    Player createPlayer(@RequestBody Player player) {
        playerService.createPlayer(player);
        return player;
    }

    @GetMapping("/player")
    Flux<Player> readAllPlayers() {
        return playerService.readAllPlayer();
    }

    @GetMapping("/player/{id}")
    Mono<Player> readPlayer(@PathVariable("id") String idPLayer) {
        return playerService.readPlayer(idPLayer);
    }

    @GetMapping("/player/name/{name}")
    Flux<Player> findPlayerByName(@PathVariable("name") String name) {
        return playerService.findPlayerByName(name);
    }

    @GetMapping("/player/rank/{rank}")
    Flux<Player> findPlayerByRank(@PathVariable("rank") String rank) {
        return playerService.findPlayerByRank(rank);
    }

    @DeleteMapping("/player/delete/{id}")
    void deletePlayer(@PathVariable("id") String idPlayer) {
        playerService.deletePlayer(idPlayer);
    }

    @DeleteMapping("/player/delete")
    String deleteAllPlayers() {
        playerService.deleteAllPlayers();
        return "All players have been deleted";
    }

    //Movement part

    @PostMapping("/movement")
    void createMovement(@RequestBody Movement movement) {
        movementService.createMovement(movement);
    }

    //Board part

    //Creación del tablero inicial
    @PostMapping
    String createBoard(@RequestBody Game game) {

        Board board =new Board();

        board.setIdBoard(game.getId());


        boardService.createBoard(board);
        return "Board with ID: " + board.getIdBoard() + " and name: " + board.getBoardName() + " has been created";
    }

    //Devuelve todos los tableros existentes

    @GetMapping
    Flux<Board> readAllBoards() {
        return boardService.readAllBoards();
    }

    //Devuelve el tablero con la ID seleccionada
    @GetMapping("/{id}")
    Mono<Board> readBoard(@PathVariable("id") String idBoard) {
        return boardService.readBoard(idBoard);
    }

    //Busca partidas que estén acabadas o no dependiendo del booleano otorgado
    //isFinished = true -> Partidas acabadas
    //isFinished = false -> Partidas en proceso
    @GetMapping("/isFinished/{bool}")
    Flux<Board> findByIsFinished(@PathVariable("bool") boolean bool) {
        return boardService.findByIsFinished(bool);
    }

    //Muestra el historial de movimientos del tablero seleccionado
    @GetMapping("history/{id}")
    void readAllMoves(@PathVariable("id") String idBoard) {
        Board boardTesting = boardService.readBoard(idBoard).block();
        boardTesting.showHistoryMoves();
    }

    //Se muestra el tamaño del tablero de juego (la matriz que estamos usando actualmente)
    @GetMapping("/size/{idBoard}")
    String getSizeBoard(@PathVariable("idBoard") String idBoard) {
        Board board = boardService.readBoard(idBoard).block();
        return board.obtainSize();
    }

    //Borrado de uno de los tableros
    @DeleteMapping("/{id}")
    void deleteBoard(@PathVariable("id") String idBoard) {
        boardService.deleteBoard(idBoard);
    }

    //Borrado de todos los tableros (así podemos controlar y evitar problemas)
    @DeleteMapping
    void deleteAllBoards() {
        boardService.deleteAllBoards();
    }

    //Añade jugador a tablero
    @PutMapping("{idPlayer}/{idBoard}")
    String addPlayerToBoard(@PathVariable("idPlayer") String idPlayer, @PathVariable("idBoard") String idBoard) {
        String state = "";
        Player player = playerService.readPlayer(idPlayer).block();
        Board board = boardService.readBoard(idBoard).block();

        //Comprobación sobre si los jugadores están ocupados
        if (board.getPlayerOne() == null) {
            board.setPlayerOne(player);
            boardService.updateBoard(board);
            state = "Added player: " + player.getId() + " to board: " + board.getIdBoard();
        } else if (board.getPlayerTwo() == null) {
            board.setPlayerTwo(player);
            boardService.updateBoard(board);
            state = "Added player: " + player.getId() + " to board: " + board.getIdBoard();
        } else if (board.getPlayerOne() != null && board.getPlayerTwo() != null) {
            state =  "Both players have already been added";
        }

        return state;
    }

    //Cierra el tablero para que no puedan introducirse más movimientos
    @PutMapping("/close/{idBoard}")
    String closeGameBoard(@PathVariable("idBoard") String idBoard ) {
        Board board = boardService.readBoard(idBoard).block();
        board.setFinished(true);
        boardService.updateBoard(board);
        return "Board " + board.getIdBoard() + " has been closed";
    }

    //Abre el tablero para que pueda continuarse la partida
    @PutMapping("/open/{idBoard}")
    String openGameBoard(@PathVariable("idBoard") String idBoard) {
        Board board = boardService.readBoard(idBoard).block();
        board.setFinished(false);
        boardService.updateBoard(board);
        return "Board " + board.getIdBoard() + " has been opened";
    }

    //Se introduce la id del tablero, el número del jugador (1 o 2) y luego la columna en que se depositará la ficha)
    @PutMapping("/{idBoard}/{playerNumber}/{column}")
    void putCoinIntoBoard(@PathVariable("idBoard") String idBoard, @PathVariable("playerNumber") Integer playerNumber, @PathVariable("column") Integer column) {
        Board board = boardService.readBoard(idBoard).block();
        playerMove(board, playerNumber, column);
        Player player = new Player();

        //En caso de que sea un jugador u otro, que coja a los jugadores que han sido añadidos al principio
        if (playerNumber == 1) {
            player = board.getPlayerOne();
        } else if (playerNumber == 2) {
            player = board.getPlayerTwo();
        }

        //Sacamos el arraylist del histórico de movimientos para luego modificarlo, volver a añadirlo al tablero y luego updatearlo
        ArrayList<Movement> movesRest = board.getMoves();
        Movement move = new Movement(column, player);
        movesRest.add(move);
        board.setMoves(movesRest);
        //Subimos la versión actualizada con los nuevos movimientos en la base de datos
        boardService.updateBoard(board);
    }

    //métodos auxiliares
    public static void playerMove(Board board, int playerNumber, int column) {

        if (playerNumber == 1) {
            System.out.println( board.getPlayerOne().getName() + ", introduce las columna");

            System.out.println("\nColumn chosen by " + board.getPlayerOne().getName() + " is " + column);
            board.putCoin(column, board.getPlayerOne(), playerNumber);
        } else if (playerNumber == 2) {
            System.out.println( board.getPlayerTwo().getName() + ", introduce las columna");

            System.out.println("\nColumn chosen by " + board.getPlayerTwo().getName() + " is " + column);
            board.putCoin(column, board.getPlayerTwo(), playerNumber);
        }
    }
}
