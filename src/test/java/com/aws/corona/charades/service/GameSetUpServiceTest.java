package com.aws.corona.charades.service;

import com.aws.corona.charades.GameObjectMother;
import com.aws.corona.charades.TeamObjectMother;
import com.aws.corona.charades.domain.CategoryMap;
import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.TeamsViewForm;
import com.aws.corona.charades.repositories.GameRepository;
import com.aws.corona.charades.repositories.UuidGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameSetUpServiceTest {

    private static final String GENERAL = "General";
    private static final String FOOD = "Food";
    private static final String ANIMALS = "Animals";
    private CategoryMap categoryMap = new CategoryMap(new HashMap<>());
    private GameService gameService = new GameService(new GameRepository(new HashMap<>(), new UuidGeneratorImpl()));
    private GameSetUpService testObj = new GameSetUpService(categoryMap, gameService);
    private Game game;
    private TeamsViewForm teamsViewForm;

    @BeforeEach
    void setUp() {
        UUID uuid = UUID.randomUUID();
        game = GameObjectMother.buildGame(uuid, TeamObjectMother.createTeamOne(), TeamObjectMother.createTeamTwo(), new ArrayList<>(), new ArrayList<>(), "mockCurrentWord", new Player(), true);
        teamsViewForm = new TeamsViewForm(uuid,3,3,6, GENERAL);
    }

    @Test
    void setUpGameShouldAddCorrectNumberOfPlayersToTeamOne() {
        //arrange...handled in set up

        //act
        testObj.setUpGame(teamsViewForm, game);

        //assert
        assertEquals(teamsViewForm.getNumPlayersTeamOne().intValue(), game.getTeamOne().getPlayers().size());
    }

    @Test
    void setUpGameShouldAddCorrectNumberOfPlayersToTeamTwo() {
        teamsViewForm.setNumPlayersTeamTwo(4);
        testObj.setUpGame(teamsViewForm, game);

        assertEquals(4, game.getTeamTwo().getPlayers().size());
    }

    @Test
    void setUpGameShouldAddDefaultPlayerNamesToTeamOne() {
        testObj.setUpGame(teamsViewForm, game);

        assertTrue(game.getTeamOne().getPlayers().get(0).getName().equalsIgnoreCase("Player 1"));
        assertTrue(game.getTeamOne().getPlayers().get(1).getName().equalsIgnoreCase("Player 2"));
        assertTrue(game.getTeamOne().getPlayers().get(2).getName().equalsIgnoreCase("Player 3"));
    }

    @Test
    void setUpGameShouldAddDefaultPlayerNamesToTeamTwo() {
        teamsViewForm.setNumPlayersTeamTwo(4);
        testObj.setUpGame(teamsViewForm, game);

        assertTrue(game.getTeamTwo().getPlayers().get(0).getName().equalsIgnoreCase("Player 1"));
        assertTrue(game.getTeamTwo().getPlayers().get(1).getName().equalsIgnoreCase("Player 2"));
        assertTrue(game.getTeamTwo().getPlayers().get(2).getName().equalsIgnoreCase("Player 3"));
        assertTrue(game.getTeamTwo().getPlayers().get(3).getName().equalsIgnoreCase("Player 4"));
    }

    @Test
    void setUpGameShouldAddCorrectNumberOfWordsToGame() {
        int expectedNumWords = (teamsViewForm.getNumPlayersTeamOne() + teamsViewForm.getNumPlayersTeamTwo()) * teamsViewForm.getNumWordsPerPlayer();
        testObj.setUpGame(teamsViewForm, game);

        assertEquals(expectedNumWords, game.getActiveWords().size());
    }

    @Test
    void setUpGameShouldPullWordsFromCorrectFileGeneral() {
        teamsViewForm.setSelectedCategoryName(GENERAL);

        testObj.setUpGame(teamsViewForm, game);
        String word = game.getActiveWords().get(0);

        assertTrue(resourceFileContainsWord(categoryMap.getCategoryFilePathMap().get(GENERAL), word));
    }

    @Test
    void setUpGameShouldPullWordsFromCorrectFileFood() {
        teamsViewForm.setSelectedCategoryName(FOOD);

        testObj.setUpGame(teamsViewForm, game);
        String word = game.getActiveWords().get(0);

        assertTrue(resourceFileContainsWord(categoryMap.getCategoryFilePathMap().get(FOOD), word));
    }

    @Test
    void setUpGameShouldPullWordsFromCorrectFileAnimals() {
        teamsViewForm.setSelectedCategoryName(ANIMALS);

        testObj.setUpGame(teamsViewForm, game);
        String word = game.getActiveWords().get(0);

        assertTrue(resourceFileContainsWord(categoryMap.getCategoryFilePathMap().get(ANIMALS), word));
    }

    private boolean resourceFileContainsWord(String filePath, String word){
        InputStream in = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.lines().anyMatch(line -> line.equalsIgnoreCase(word));
    }

}