package com.example.rayas.board.infrastructure.repository;


import com.example.rayas.board.domain.Board;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BoardRepository extends ReactiveMongoRepository<Board, String> {

    Flux<Board> findByIsFinished(boolean bool);
}
