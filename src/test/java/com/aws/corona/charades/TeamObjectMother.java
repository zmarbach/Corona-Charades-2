package com.aws.corona.charades;

import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamObjectMother {

    private TeamObjectMother(){}

    public static Team createTeam(String teamName, Integer score, List<Player> players, Player prevPlayer){
        Team team = new Team();
        team.setName(teamName);
        team.setScore(score);
        team.setPlayers(players);
        team.setPreviousPlayer(prevPlayer);

        return team;
    }

    public static Team createTeamOne(){
        return createTeam("Team One", 0, new ArrayList<>(), new Player());
    }

    public static Team createTeamTwo(){
        return createTeam("Team Two", 0, new ArrayList<>(), new Player());
    }
}
