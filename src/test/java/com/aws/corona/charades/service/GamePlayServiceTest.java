package com.aws.corona.charades.service;

import com.aws.corona.charades.GameObjectMother;
import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.repositories.GameRepository;
import com.aws.corona.charades.repositories.UuidGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GamePlayServiceTest {

    private GameRepository gameRepository = new GameRepository(new HashMap<>(), new UuidGeneratorImpl());
    private GamePlayService testObj = new GamePlayService(new GameService(gameRepository));
    private Game game;

    @BeforeEach
    void setUp() {
        game = GameObjectMother.setUpGame();
    }

    @Test
    void handleStartTurnShouldRandomlySetCurrentWordInGame() {
        //arrange...handled in set up

        //act
            testObj.handleStartTurn(game);
        //assert
            assertNotNull(game.getCurrentWord());
            assertNotEquals("mockCurrentWord", game.getCurrentWord());
    }

    @Test
    void handleStartTurnShouldMakeNewTurnFalse() {
        testObj.handleStartTurn(game);
        assertFalse(game.isNewTurn());
    }

    @Test
    void handleCorrectShouldRemoveWordFromActiveWordsAndAddItToGuessedWords() {
        game.setCurrentWord(game.getActiveWords().get(0));

        testObj.handleCorrect(game);

        assertTrue(!game.getActiveWords().contains("alpha"));
        assertTrue(game.getGuessedWords().contains("alpha"));
    }

    @Test
    void handleCorrectShouldIncrementTheCurrentTeamScore() {
        Integer initialScore = game.getTeamOne().getScore();
        Integer team2ScoreBefore = game.getTeamTwo().getScore();

        testObj.handleCorrect(game);
        Integer incrementedScore = game.getTeamOne().getScore();
        Integer team2ScoreAfter = game.getTeamTwo().getScore();

        assertEquals(initialScore += 1, incrementedScore);
        assertEquals(team2ScoreBefore, team2ScoreAfter);
    }

    @Test
    void handleCorrectShouldMakeNextWordInActiveWordsTheCurrentWordInGame() {
        List<String> activeWords = new ArrayList<>();
        activeWords.add("foo");
        activeWords.add("bar");
        game.setActiveWords(activeWords);
        game.setCurrentWord(game.getActiveWords().get(0));

        testObj.handleCorrect(game);

        assertEquals("bar", game.getCurrentWord());
    }

    @Test
    void handleCorrectShouldNOTSetCurrentWordIfOnlyOneWordLeft() {
        List<String> activeWords = new ArrayList<>();
        activeWords.add("test");
        game.setActiveWords(activeWords);
        game.setCurrentWord(game.getActiveWords().get(0));
        String currentWordBefore = game.getActiveWords().get(0);

        testObj.handleCorrect(game);

        assertEquals(currentWordBefore, game.getCurrentWord());
    }

    @Test
    void handleSkipShouldKeepWordInListOfActiveWords() {
        game.setCurrentWord(game.getActiveWords().get(0));
        testObj.handleSkip(game);
        assertTrue(game.getActiveWords().contains("alpha"));
    }

    @Test
    void handleSkipShouldMakeCurrentWordTheNextWordInListOfActiveWords() {
        game.setCurrentWord(game.getActiveWords().get(0));
        testObj.handleSkip(game);
        assertEquals("bravo", game.getCurrentWord());
    }

    @Test
    void handleNextPlayerShouldSetCurrentPlayerAsCurrentTeamPrevPlayer() {
        testObj.handleNextPlayer(game);
        assertEquals(game.getTeamOne().getPlayers().get(0), game.getTeamOne().getPreviousPlayer());
    }

    @Test
    void handleNextPlayerShouldSetNewCurrentPlayerProperly() {
        Player edgar = new Player("Edgar", game.getTeamTwo());
        game.getTeamTwo().getPlayers().add(edgar);
        game.getTeamTwo().setPreviousPlayer(game.getTeamTwo().getPlayers().get(1));
        testObj.handleNextPlayer(game);
        assertEquals(edgar.getName(), game.getCurrentPlayer().getName());
    }

    @Test
    void handleNextPlayerShouldSetNewCurrentPlayerProperlyNonHappyPath() {
        game.getTeamOne().setPreviousPlayer(game.getTeamOne().getPlayers().get(0));
        game.setCurrentPlayer(game.getTeamTwo().getPlayers().get(0));
        testObj.handleNextPlayer(game);
        assertEquals(game.getTeamOne().getPlayers().get(1).getName(), game.getCurrentPlayer().getName());
    }

    @Test
    void handleNextPlayerShouldSetNewCurrentPlayerProperlyIfPrevPlayerIsLastInListOfPlayers() {
        game.getTeamTwo().setPreviousPlayer(game.getTeamTwo().getPlayers().get(1));
        testObj.handleNextPlayer(game);
        assertEquals(game.getTeamTwo().getPlayers().get(0).getName(), game.getCurrentPlayer().getName());
    }

    @Test
    void handleNextPlayerShouldSetNewTurnToTrue(){
        testObj.handleNextPlayer(game);
        assertTrue(game.isNewTurn());
    }

    @Test
    void handleNextRoundShouldPutAllWordsBackInActiveWordsList() {
        int numOfWordsBeforeNextRound = game.getActiveWords().size();
        simulateAllWordsGuessed(game);
        testObj.handleNextRound(game);
        int numOfWordsAfterNextRound = game.getActiveWords().size();
        assertEquals(numOfWordsBeforeNextRound, numOfWordsAfterNextRound);
    }

    @Test
    void handleNextRoundShouldResetGuessedWordsToEmptyList() {
        simulateAllWordsGuessed(game);
        testObj.handleNextRound(game);
        assertTrue(game.getGuessedWords().isEmpty());
    }

    @Test
    void handleNextRoundShouldSetNextPlayerProperly() {
        game.getTeamTwo().setPreviousPlayer(game.getTeamTwo().getPlayers().get(1));
        testObj.handleNextRound(game);
        assertEquals(game.getTeamTwo().getPlayers().get(0).getName(), game.getCurrentPlayer().getName());
    }

    @Test
    void handleEndGameShouldRemoveGameFromGameRepository() {
        UUID oldGameUUID = game.getUuid();
        testObj.handleEndGame(game);
        assertNull(gameRepository.getGame(oldGameUUID));
    }

    private void simulateAllWordsGuessed(Game game){
        int numOfWords = game.getActiveWords().size();
        for (int i = 0; i < numOfWords; i++) {
            testObj.handleCorrect(game);
        }
    }
}