package com.example.rayas.user.service;

import com.example.rayas.user.infraestructure.repository.UserRepository;
import com.example.rayas.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Mono<User> addPlayer(User user) {
        return userRepository.save(user);
    }

    @Override
    public Flux<User> getAllPlayers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public boolean checkPlayer(User user) {
        Mono<User> checkPlayerMono = userRepository.findByUserName(user.getUserName());
        Optional<User> checkPlayerResult = checkPlayerMono.blockOptional();
        // Si no existe el usuario lo crea y devuelve true al front. La contraseña se guarda encriptada
        if(checkPlayerResult.isEmpty()){
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            userRepository.save(user).subscribe();
            return true;
        }
        // Si existe y se puso bien la contraseña devuelve true al front
        if(BCrypt.checkpw(user.getPassword(),checkPlayerResult.get().getPassword())){
            return true;
        }
        // Si no coincide la contraseña devuelve false al front
        else{
            return false;
        }
    }
}
