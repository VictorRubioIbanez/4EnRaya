package com.example.rayas.game.infraestructure.repository;

import com.example.rayas.game.model.Game;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {



}
