package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Player {
    private String name;
    private Team team;

    public Player() {
    }

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team.getName());
    }
}
