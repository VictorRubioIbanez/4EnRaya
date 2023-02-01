package com.example.rayas.player.application;


import com.example.rayas.player.domain.Player;
import com.example.rayas.player.infrastructure.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public String createPlayer(Player player) {
        playerRepository.save(player).subscribe();
        return "Player " + player.getName() + " has been created";
    }

    @Override
    public Mono<Player> readPlayer(String idPlayer) {
        return playerRepository.findById(idPlayer);
    }

    @Override
    public Flux<Player> readAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public Flux<Player> findPlayerByName(String playerName) {
        return playerRepository.findPlayerByName(playerName);
    }

    @Override
    public Flux<Player> findPlayerByRank(String playerRank) {
        return playerRepository.findPlayerByRank(playerRank);
    }

    @Override
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void deletePlayer(String idPlayer) {
        playerRepository.deleteById(idPlayer).subscribe();
    }

    @Override
    public void deleteAllPlayers() {
        playerRepository.deleteAll().subscribe();
    }
}
