package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GamePlayViewForm {
    private UUID gameUUID;
    private String currentWord;
    private Player currentPlayer;
    private Integer teamOneScore;
    private Integer teamTwoScore;
    private List<String> activeWords;
    private boolean newTurn;

    public GamePlayViewForm() {
    }

    public GamePlayViewForm(UUID gameUUID, String currentWord, Player currentPlayer, Integer teamOneScore, Integer teamTwoScore, List<String> activeWords, boolean isNewTurn) {
        this.gameUUID = gameUUID;
        this.currentWord = currentWord;
        this.currentPlayer = currentPlayer;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
        this.activeWords = activeWords;
        this.newTurn = isNewTurn;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Integer getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(Integer teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public Integer getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(Integer teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public List<String> getActiveWords() {
        return activeWords;
    }

    public void setActiveWords(List<String> activeWords) {
        this.activeWords = activeWords;
    }

    public boolean isNewTurn() {
        return newTurn;
    }

    public void setNewTurn(boolean newTurn) {
        this.newTurn = newTurn;
    }
}
