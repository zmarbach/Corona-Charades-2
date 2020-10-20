package com.aws.corona.charades;

import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerObjectMother {

    private PlayerObjectMother(){}

    public static Player createPlayer(String playerName, Team team){
        Player player = new Player();
        player.setName(playerName);
        player.setTeam(team);

        return player;
    }
}
