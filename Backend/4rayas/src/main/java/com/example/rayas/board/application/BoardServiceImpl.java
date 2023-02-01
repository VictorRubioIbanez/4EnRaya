package com.example.rayas.board.application;


import com.example.rayas.board.domain.Board;
import com.example.rayas.board.infrastructure.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public String createBoard(Board board) {
        boardRepository.save(board).subscribe();
        return "Board has been created";
    }

    @Override
    public Mono<Board> readBoard(String idBoard) {
        return boardRepository.findById(idBoard);
    }

    @Override
    public Flux<Board> readAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Flux<Board> findByIsFinished(boolean bool) {
        return boardRepository.findByIsFinished(bool);
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.save(board).subscribe();
    }

    @Override
    public void deleteBoard(String idBoard) {
        boardRepository.deleteById(idBoard).subscribe();
    }

    @Override
    public String deleteAllBoards() {
        boardRepository.deleteAll().subscribe();
        return "All boards have been deleted";
    }


}
