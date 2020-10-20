package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PlayerForm {
    private List<Player> players = new ArrayList<>();
    private UUID gameUUID;

    public PlayerForm() {
    }

    public PlayerForm(List<Player> players, UUID gameUUID) {
        this.players = players;
        this.gameUUID = gameUUID;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public void setGameUUID(UUID gameUUID) {
        this.gameUUID = gameUUID;
    }
}
