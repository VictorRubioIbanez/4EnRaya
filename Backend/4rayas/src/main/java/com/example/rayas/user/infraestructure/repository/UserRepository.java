package com.example.rayas.user.infraestructure.repository;

import com.example.rayas.user.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUserName(String userName);
}
