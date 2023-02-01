package com.example.rayas.user.service;

import com.example.rayas.user.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> addPlayer(User user);
    Flux<User> getAllPlayers();
    Mono<Void> deleteUser(String id);
    boolean checkPlayer(User user);
}
