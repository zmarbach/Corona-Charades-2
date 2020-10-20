package com.aws.corona.charades.repositories;

import com.aws.corona.charades.GameObjectMother;
import com.aws.corona.charades.TeamObjectMother;
import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Team;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    private static final UUID HARDCODED_UUID = new UUID(1,1);
    private GameRepository testObj;
    private Game game;
    private Map<UUID, Game> gameMap = new HashMap<>();

    //set this up so can refer to in later test methods where we expect an exception to be thrown
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @BeforeEach
    void setUp() {
        game = GameObjectMother.setUpGame();
        gameMap.put(game.getUuid(), game);
        testObj = new GameRepository(gameMap, new UuidGeneratorImpl());
    }

    @Test
    void getGameShouldFindGameIfPresent() {
        Game retrievedGame = testObj.getGame(game.getUuid());
        assertEquals(game, retrievedGame);
    }

    @Test
    void getGameShouldThrowExceptionIfGameNotFound() {
        UUID badUUID = new UUID(2,2);
        testObj.getGame(game.getUuid());

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("No game found for id of : " + badUUID.toString());
    }

    @Test
    void createNewGame() {
        Game newGame = testObj.createNewGame();
        assertEquals(gameMap.size(), 2);
        assertTrue(gameMap.containsKey(newGame.getUuid()));
    }

    @Test
    void updateGame() {
        String testWord = "pineapple";
        game.setCurrentWord(testWord);
        testObj.updateGame(game);
        assertEquals(testWord, gameMap.get(game.getUuid()).getCurrentWord());
    }

    @Test
    void deleteGame() {
        testObj.deleteGame(game);
        assertEquals(gameMap.size(), 0);
        assertFalse(gameMap.containsKey(game.getUuid()));
    }
}