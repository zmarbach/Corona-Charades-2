package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findGameById(UUID uuid){
        return gameRepository.getGame(uuid);
    }

    public Game createNewGame(){
        return gameRepository.createNewGame();
    }

    public void updateGame(Game game){
        gameRepository.updateGame(game);
    }

    public void deleteGame(Game game){
        gameRepository.deleteGame(game);
    }
}
