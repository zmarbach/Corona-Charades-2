package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GamePlayService {
    private Random r = new Random();
    private GameService gameService;

    public GamePlayService(GameService gameService) {
        this.gameService = gameService;
    }

    public void handleStartTurn(Game game){
        List<String> activeWords = game.getActiveWords();
        String randomWord = activeWords.get(r.nextInt(activeWords.size()));
        game.setCurrentWord(randomWord);
        game.setNewTurn(false);
    }

    public void handleCorrect(Game game) {
        String correctlyGuessedWord = game.getCurrentWord();
        int currentWordIndex = determineCurrentWordIndex(game);

        //if more than one word left
        if(game.getActiveWords().size() > 1){
            game.setCurrentWord(determineNextWord(currentWordIndex, game));
        }
        game.getActiveWords().remove(correctlyGuessedWord);
        game.getGuessedWords().add(correctlyGuessedWord);

        incrementCurrentTeamScore(game);
        gameService.updateGame(game);
    }

    public void handleSkip(Game game) {
        int currentWordIndex = determineCurrentWordIndex(game);
        game.setCurrentWord(determineNextWord(currentWordIndex, game));
        gameService.updateGame(game);
    }

    public void handleNextPlayer(Game game) {
        setCurrentTeamsPreviousPlayer(game.getCurrentPlayer());
        setNewCurrentPlayer(game);
        game.setNewTurn(true);
        gameService.updateGame(game);
    }

    public void handleNextRound(Game game) {
        game.setActiveWords(game.getGuessedWords());
        game.setGuessedWords(new ArrayList<>());
        Collections.shuffle(game.getActiveWords());

        handleNextPlayer(game);
        gameService.updateGame(game);
    }

    public void handleEndGame(Game game) {
        gameService.deleteGame(game);
    }

    private void setNewCurrentPlayer(Game game) {
        Team currentTeam = game.getCurrentPlayer().getTeam();
        Team newTeam;
        //switch teams
        if(currentTeam.equals(game.getTeamOne())){
            newTeam = game.getTeamTwo();
        }
        else{
            newTeam = game.getTeamOne();
        }

        //set currentPlayer based on new team and previousPlayer
        int prevPlayerIndex = newTeam.getPlayers().indexOf(newTeam.getPreviousPlayer());
        if (currentElementIsLastElementInList(prevPlayerIndex, newTeam.getPlayers())) {
            game.setCurrentPlayer(newTeam.getPlayers().get(0));
        } else {
            game.setCurrentPlayer(newTeam.getPlayers().get(prevPlayerIndex + 1));
        }
    }

    private void setCurrentTeamsPreviousPlayer(Player currentPlayer) {
        currentPlayer.getTeam().setPreviousPlayer(currentPlayer);
    }

    private void incrementCurrentTeamScore(Game game) {
        Team currentTeam = game.getCurrentPlayer().getTeam();
        currentTeam.setScore(currentTeam.getScore() + 1);
    }

    private int determineCurrentWordIndex(Game game){
        return game.getActiveWords().indexOf(game.getCurrentWord());
    }

    private String determineNextWord(int currentWordIndex, Game game) {
        if(currentElementIsLastElementInList(currentWordIndex, game.getActiveWords())){
            return game.getActiveWords().get(0);
        }
        else{
            return game.getActiveWords().get(currentWordIndex + 1);
        }
    }

    private boolean currentElementIsLastElementInList(int elementIndex, List list){
        return elementIndex == list.size() - 1;
    }
}
