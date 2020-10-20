package com.aws.corona.charades;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class GameObjectMother {

    private GameObjectMother(){
    }

    public static Game buildGame(UUID uuid, Team teamOne, Team teamTwo, List<String> activeWords, List<String> guessedWords, String currentWord, Player currentPlayer, boolean newTurn){
        return new Game.GameBuilder()
                .withUUID(uuid)
                .withTeamOne(teamOne)
                .withTeamTwo(teamTwo)
                .withActiveWords(activeWords)
                .withGuessedWords(guessedWords)
                .withCurrentWord(currentWord)
                .withCurrentPlayer(currentPlayer)
                .isNewTurn(newTurn)
                .build();
    }

    public static Game setUpGame(){
        Team team1 = TeamObjectMother.createTeam("Team One", 0, new ArrayList<>(), new Player());
        Player team1Player1 = PlayerObjectMother.createPlayer("Andrew", team1);
        Player team1Player2 = PlayerObjectMother.createPlayer("Billy", team1);
        setPlayersAndPrevPlayer(team1, team1Player1, Arrays.asList(team1Player1, team1Player2));

        Team team2 = TeamObjectMother.createTeam("Team Two", 0, new ArrayList<>(), new Player());
        Player team2Player1 = PlayerObjectMother.createPlayer("Chad", team2);
        Player team2Player2 = PlayerObjectMother.createPlayer("Derek", team2);
        setPlayersAndPrevPlayer(team2, team2Player1, Arrays.asList(team2Player1, team2Player2));

        List<String> activeWords = createActiveWords();
        return buildGame(UUID.randomUUID(), team1, team2, activeWords, new ArrayList<>(), "mockCurrentWord", team1.getPlayers().get(0), true );
    }

    private static List<String> createActiveWords() {
        return new ArrayList<>(Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", "india", "juliette"));
    }

    private static void setPlayersAndPrevPlayer(Team team, Player prevPlayer, List<Player> players) {
        for (Player player : players) {
            team.getPlayers().add(player);
        }

        team.setPreviousPlayer(prevPlayer);
    }
}
