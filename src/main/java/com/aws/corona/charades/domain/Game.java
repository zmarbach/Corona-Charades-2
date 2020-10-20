package com.aws.corona.charades.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Game {
    private UUID uuid;
    private Team teamOne;
    private Team teamTwo;
    private List<String> activeWords;
    private List<String> guessedWords;
    private String currentWord;
    private Player currentPlayer;
    private boolean newTurn;

    public Game(UUID uuid) {
        this.uuid = uuid;
        this.teamOne = new Team("Team One", new ArrayList<>(), 0, new Player());
        this.teamTwo = new Team("Team Two", new ArrayList<>(), 0, new Player());
        this.activeWords = new ArrayList<>();
        this.guessedWords = new ArrayList<>();
        this.currentWord = "";
        this.currentPlayer = new Player("DEREK JETER", new Team("", new ArrayList<>(), 0, new Player()));
        this.newTurn = true;
    }

    public static class GameBuilder{
        UUID uuid = null;
        Team teamOne = new Team();
        Team teamTwo = new Team();
        List<String> activeWords = new ArrayList<>();
        List<String> guessedWords = new ArrayList<>();
        String currentWord = "";
        Player currentPlayer = new Player();
        boolean newTurn = false;

        public GameBuilder withUUID(UUID uuid){
            this.uuid = uuid;
            return this;
        }

        public GameBuilder withTeamOne(Team teamOne){
            this.teamOne = teamOne;
            return this;
        }

        public GameBuilder withTeamTwo(Team teamTwo){
            this.teamTwo = teamTwo;
            return this;
        }

        public GameBuilder withActiveWords(List<String> activeWords){
            this.activeWords = activeWords;
            return this;
        }

        public GameBuilder withGuessedWords(List<String> guessedWords){
            this.guessedWords = guessedWords;
            return this;
        }

        public GameBuilder withCurrentWord(String currentWord){
            this.currentWord = currentWord;
            return this;
        }

        public GameBuilder withCurrentPlayer(Player currentPlayer){
            this.currentPlayer = currentPlayer;
            return this;
        }

        public GameBuilder isNewTurn(boolean newTurn){
            this.newTurn = newTurn;
            return this;
        }

        public Game build(){
            Game game = new Game(this.uuid);
            game.setTeamOne(this.teamOne);
            game.setTeamTwo(this.teamTwo);
            game.setActiveWords(this.activeWords);
            game.setGuessedWords(this.guessedWords);
            game.setCurrentWord(this.currentWord);
            game.setCurrentPlayer(this.currentPlayer);
            game.setNewTurn(this.newTurn);

            return game;
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public List<String> getActiveWords() {
        return activeWords;
    }

    public void setActiveWords(List<String> activeWords) {
        this.activeWords = activeWords;
    }

    public List<String> getGuessedWords() {
        return guessedWords;
    }

    public void setGuessedWords(List<String> guessedWords) {
        this.guessedWords = guessedWords;
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

    public boolean isNewTurn() {
        return newTurn;
    }

    public void setNewTurn(boolean newTurn) {
        this.newTurn = newTurn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return newTurn == that.newTurn &&
                Objects.equals(teamOne, that.teamOne) &&
                Objects.equals(teamTwo, that.teamTwo) &&
                Objects.equals(activeWords, that.activeWords) &&
                Objects.equals(guessedWords, that.guessedWords) &&
                Objects.equals(currentWord, that.currentWord) &&
                Objects.equals(currentPlayer, that.currentPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamOne, teamTwo, activeWords, guessedWords, currentWord, currentPlayer, newTurn);
    }
}
