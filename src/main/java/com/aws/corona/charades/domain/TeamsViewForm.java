package com.aws.corona.charades.domain;

import java.util.UUID;

public class TeamsViewForm {

    private UUID gameUUID;
    private Integer numPlayersTeamOne;
    private Integer numPlayersTeamTwo;
    private Integer numWordsPerPlayer;
    private String selectedCategoryName;

    public TeamsViewForm(UUID gameUUID, Integer numPlayersTeamOne, Integer numPlayersTeamTwo, Integer numWordsPerPlayer, String selectedCategoryName) {
        this.gameUUID = gameUUID;
        this.numPlayersTeamOne = numPlayersTeamOne;
        this.numPlayersTeamTwo = numPlayersTeamTwo;
        this.numWordsPerPlayer = numWordsPerPlayer;
        this.selectedCategoryName = selectedCategoryName;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public Integer getNumPlayersTeamOne() {
        return numPlayersTeamOne;
    }

    public void setNumPlayersTeamOne(Integer numPlayersTeamOne) {
        this.numPlayersTeamOne = numPlayersTeamOne;
    }

    public Integer getNumPlayersTeamTwo() {
        return numPlayersTeamTwo;
    }

    public void setNumPlayersTeamTwo(Integer numPlayersTeamTwo) {
        this.numPlayersTeamTwo = numPlayersTeamTwo;
    }

    public Integer getNumWordsPerPlayer() {
        return numWordsPerPlayer;
    }

    public void setNumWordsPerPlayer(Integer numWordsPerPlayer) {
        this.numWordsPerPlayer = numWordsPerPlayer;
    }

    public String getSelectedCategoryName() {
        return selectedCategoryName;
    }

    public void setSelectedCategoryName(String selectedCategoryName) {
        this.selectedCategoryName = selectedCategoryName;
    }
}
