package com.aws.corona.charades.repositories;

import com.aws.corona.charades.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class GameRepository {
    private Map<UUID, Game> gameMap;
    private UuidGenerator uuidGenerator;

    public GameRepository(Map<UUID, Game> gameMap, UuidGenerator uuidGenerator) {
        this.gameMap = gameMap;
        this.uuidGenerator = uuidGenerator;
    }

    public Game getGame(UUID uuid) {
        try{
            return gameMap.get(uuid);
        }
        catch (Exception e){
            System.out.println("No game found for id of : " + uuid.toString());
            return null;
        }
    }

    public Game createNewGame(){
        Game game = new Game(uuidGenerator.generateUUID());
        gameMap.put(game.getUuid(), game);
        return game;
    }

    public void updateGame(Game game){
        if(gameMap.containsKey(game.getUuid())){
            //override old game with new game
            gameMap.put(game.getUuid(), game);
        }
    }

    public void deleteGame(Game game){
        if(gameMap.containsKey(game.getUuid())){
            gameMap.remove(game.getUuid());
        }
    }
}
