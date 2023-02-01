package com.example.rayas.user.infraestructure.controller;

import com.example.rayas.user.model.User;
import com.example.rayas.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserServiceImpl playerServiceImpl;

    @PostMapping
    public boolean checkPlayer(@RequestBody User user){
        return playerServiceImpl.checkPlayer(user);
    }

<<<<<<< HEAD:Backend/4rayas/src/main/java/com/example/rayas/user/infraestructure/controller/UserController.java
    @GetMapping("/all")
    public Flux<User> getAllPlayers(){
        return playerServiceImpl.getAllPlayers();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id){
        return playerServiceImpl.deleteUser(id);
    }
=======
>>>>>>> andres:Backend/4rayas/src/main/java/com/example/rayas/player/infraestructure/controller/PlayerController.java
}
