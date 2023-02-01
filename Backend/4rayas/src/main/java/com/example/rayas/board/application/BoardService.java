package com.example.rayas.board.application;

import com.example.rayas.board.domain.Board;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoardService {

    String createBoard(Board board);
    Mono<Board> readBoard(String idBoard);
    Flux<Board> readAllBoards();
    Flux<Board> findByIsFinished(boolean bool);
    void updateBoard(Board board);
    void deleteBoard(String idBoard);
    String deleteAllBoards();

}
