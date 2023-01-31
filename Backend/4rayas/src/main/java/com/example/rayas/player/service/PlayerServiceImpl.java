package com.example.rayas.player.service;

import com.example.rayas.player.infraestructure.repository.PlayerRepository;
import com.example.rayas.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Mono<Player> addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Mono<Player> findPlayerByName(String playerName) {
        return playerRepository.findByUserName(playerName);
    }

    @Override
    public Flux<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return playerRepository.deleteById(id);
    }

    @Override
    public boolean checkPlayer(Player player) {
        Mono<Player> checkPlayerMono = playerRepository.findByUserName(player.getUserName());
        Optional<Player> checkPlayerResult = checkPlayerMono.blockOptional();
        // Si no existe el usuario lo crea y devuelve true al front. La contraseña se guarda encriptada
        if (checkPlayerResult.isEmpty()){
            String hashedPassword = BCrypt.hashpw(player.getPassword(), BCrypt.gensalt());
            player.setPassword(hashedPassword);
            playerRepository.save(player).subscribe();
            return true;
        }
        // Si existe y se puso bien la contraseña devuelve true al front
        if (BCrypt.checkpw(player.getPassword(),checkPlayerResult.get().getPassword())){
            return true;
        }
        // Si no coincide la contraseña devuelve false al front
        else {
            return false;
        }
    }
}
